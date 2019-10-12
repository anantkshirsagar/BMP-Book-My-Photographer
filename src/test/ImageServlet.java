package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("images/Ganapti.jpeg");
		byte[] byteArray = IOUtils.toByteArray(resourceAsStream);
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] encode = Base64.getEncoder().encode(byteArray);
		System.out.println("Inside doPost method");
		outputStream.write(encode, 0, encode.length);
	}

}
