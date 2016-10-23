package webClinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author Original author: Prof. Rollins. Modified by O. Karpenko.
 * HttpFetcher - shows how to sent an HTTP get request to a given webserver via the java program,
 * and how to get the response
 *
 */
public class HttpFetcher {

	public static int PORT = 80;

	/** Sends an HTTP request to fetch a given resource from the given host.
	 *  Returns the response as a string.
	 * @param host
	 * @param pathAndResource
	 * @return
	 */
	public static String fetch(String host, String pathAndResource) {

		StringBuffer buf = new StringBuffer();

		try (Socket socket = new Socket(host, PORT)) { // create a connection to the
														// web server
			OutputStream out = socket.getOutputStream(); // get the output stream from socket
			InputStream instream = socket.getInputStream(); // get the input stream from socket
			
			// wrap the input stream to make it easier to read from
			BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

			// create and send request
			String request = getRequest(host, pathAndResource);
			System.out.println("Request = " + request);
			out.write(request.getBytes());
			out.flush();

			// receive response
			// note: a better approach would be to first read headers, determine
			// content length
			// then read the remaining bytes as a byte stream
			String line = reader.readLine();
			while (line != null) {
				buf.append(line + "\n"); // append the newline stripped by
											// readline
				line = reader.readLine();
				//System.out.println(line);
			}

		} catch (IOException e) {
			System.out.println("HTTPFetcher::IOException occured during download: " + e.getMessage());
		}
		return buf.toString();

	}

	/**
	 * A method that creates a GET request for the given host and resource 
	 * @param host
	 * @param path
	 * @return
	 */
	/*private static String getRequest(String host, String pathAndResource) {
		String request = "GET " + pathAndResource + " HTTP/1.1" + System.lineSeparator()  // GET request
				+ "Host: " + host + System.lineSeparator() // Host header required for HTTP/1.1
				+ "Connection: close" + "\r\n"; // System.lineSeparator(); // make sure the server closes the
										// connection after we fetch one page
			
		return request;
	} */
	private static String getRequest(String host, String pathResourceQuery) {
		String request = "GET " + pathResourceQuery + " HTTP/1.1" + System.lineSeparator() // GET
																			// request
				+ "Host: " + host + System.lineSeparator() // Host header required for HTTP/1.1
				+ "Connection: close" + System.lineSeparator() // make sure the server closes the
															   // connection after we fetch one page
				+ System.lineSeparator();
		return request;
	}

}