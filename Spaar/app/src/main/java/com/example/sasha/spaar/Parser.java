import com.example.sasha.spaar.Models.Pair;
import com.example.sasha.spaar.Models.Product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser	 {
	
	public ArrayList<Product> GetProductsByCategory(int categoryId, int pagesNum) throws IOException{
		
		ArrayList<Product> updatedProducts = new ArrayList<Product>(); 
		
		for(int i = 0; i < pagesNum; i++)
		{
			String url = "http://myprice.org.ua/?view=products&subcat="+ categoryId +"&page=" + i;
			updatedProducts.addAll(ParcePage(url));
		}
		return updatedProducts;	
	}
	
	public ArrayList<Product> GetProductsInRange(int categoryIdFrom,int categoryIdTo, int pagesNum ) throws IOException{
		ArrayList<Product> updatedProducts = new ArrayList<Product>(); 
		for(int i = categoryIdFrom; i <= categoryIdTo; i++)
		{
			updatedProducts.addAll(GetProductsByCategory(i, pagesNum));
		}
		return updatedProducts;	
	}
	
	private static ArrayList<Product> ParcePage(String url) throws IOException	{
		// http://myprice.org.ua/?view=products&subcat=1&page=1 -- default site
				// subcat - category, page - page
				// subcat 1 - meat 
				
				ArrayList<Product> productList = new ArrayList<Product>(); 
				
				Document doc = Jsoup.connect(url).get(); //tovar
			
				Elements name = doc.select(".tovar");
				for (int i = 0; i < name.size(); i++)
					{
						Element str = name.get(i);
						String nameNode = getSupermarketName(str);
						String imageUrl = getImageUrl(str);

						int productId = getProductId(str.attr("id"));
						
						ArrayList<Pair> prices = getPrice(productId);
						
						Product product = new Product();

                        product.set_prices(prices);
                        product.set_name(nameNode);
						/*product.setPrices(prices);
						product.setName(nameNode);
						product.setImageUrl(imageUrl);*/
						productList.add(product);
					}
				
				return productList;
	}
	
	private static ArrayList<Pair> getPrice(int productId) throws IOException{
		String productIdUrl = "http://myprice.org.ua/?view=product&id="+productId;
		ArrayList<Pair> priceList = new ArrayList<Pair>();
		
		Document document = Jsoup.connect(productIdUrl).get();
		Elements supermarketElements = document.select(".sypermarketu");
		
		for (Element supermarketElement : supermarketElements) {
			Pair temp = new Pair();
			String supermarketName = supermarketElement.select(".cenaImg").attr("src");
			double price = Double.parseDouble(supermarketElement.select(".toogle_price").attr("id"));
			
			temp.setPrice(price);
			temp.setShop(supermarketName);
			priceList.add(temp);
		}
			
		return priceList;
	}
	
	private static ArrayList<Pair> getPrice(Element elem){
		Elements prices = elem.select(".cena2");
		Elements supermarkets = elem.select(".cenaImg2");
		
		ArrayList<Pair> priceList = new ArrayList<Pair>();
		
		for(int i = 0; i < prices.size(); i++){
			Pair temp = new Pair();
			String strPrice = prices.get(i).childNode(0).toString();
			double price = getSupermarketPrice(strPrice);
			
			// supermarket stores string name of supermarket image name 
			// "images/supermarket/amstor.png"
			// TO DO: in getSupermarketName() method change it for short name
			// using Enum or similar
			String supermarket = supermarkets.get(i).attr("src");
			
			temp.setPrice(price);
			temp.setShop(supermarket);
	        priceList.add(temp);
		}
		
		return priceList;
		
	}
	
	// TO DO: use enum for changing long name to short name
	private static String getSupermarketName(Element elem){
		String parsed = elem.select(".tovarJpis").toString(); 
		String name = parsed.replaceAll("\\<.*?>", "");
		System.out.println("Got the name");
		return name;
	}

	private static String getImageUrl(Element elem){
		Element image = elem.select("img").first();
		String url = image.absUrl("src");
		return url;
	}
	
	// returns double price from parsed string
	// hope, it will work in all cases...
	private static Double getSupermarketPrice(String price){
		String numberOnly = price.replaceAll("[^0-9\\.]+", "");
		double d = Double.parseDouble(numberOnly);
	    return d;
	}
	
	private static int getProductId(String id){
		String numberOnly = id.replaceAll("[^0-9\\.]+", "");
		int price = Integer.parseInt(numberOnly);
	    return price;
	}

	
}
