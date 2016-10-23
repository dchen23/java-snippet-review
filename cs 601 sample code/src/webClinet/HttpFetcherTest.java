package webClinet;

public class HttpFetcherTest {
	public static void main(String[] args) {
		
		// Use HttpFetcher to fetch a page from cs.usfca.edu/~okarpenko/infoUSF.html
		String res = HttpFetcher.fetch("www.cs.usfca.edu", "/~okarpenko/infoUSF.html");

		System.out.println(res);
		

	}
}
