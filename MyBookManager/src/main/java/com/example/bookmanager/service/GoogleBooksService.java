package com.example.bookmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.bookmanager.model.Book;

@Service
public class GoogleBooksService  {

	private static final String GOOGLE_BOOKS_API_URL="https://www.googleapis.com/books/v1/volumes";
	
	public List<Book>searchBooks(String keyword){
		List<Book>books = new ArrayList<>();
		
		String url = UriComponentsBuilder.fromUriString(GOOGLE_BOOKS_API_URL)
				.queryParam("q", keyword)
				.queryParam("maxResults", 10)
				.toUriString();
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		
		JSONObject json = new JSONObject(response);
		JSONArray items = json.optJSONArray("items");
		
		if(items != null) {
			for(int i = 0; i < items.length(); i++) {
				JSONObject item = items.getJSONObject(i);
				JSONObject volumeInfo = item.getJSONObject("volumeInfo");
				
				
				System.out.println("===== volumeInfo =====");
				System.out.println(volumeInfo.toString(2)); // 整形表示

				
				
				Book book = new Book();
				
				book.setTitle(volumeInfo.optString("title"));
				book.setAuthors(volumeInfo.optJSONArray("authors") != null
						?volumeInfo.optJSONArray("authors").join(",").replace("\"", "")
								:"著者不明");
				book.setIsbn(extractIsbn(volumeInfo));
				book.setThumbnailUrl(volumeInfo.optJSONObject("imageLinks")!=null
						?volumeInfo.getJSONObject("imageLinks").optString("thumbnail")
								:"");
				book.setPublishedDate(volumeInfo.optString("publishedDate"));
				book.setPageCount(volumeInfo.optInt("pageCount", 0));
				book.setPrintType(volumeInfo.optString("printType", "不明"));
				book.setLanguage(volumeInfo.optString("language", "不明"));
				book.setDescription(volumeInfo.optString("description", "説明なし"));
				
				books.add(book);
				
			}
		}
		
		return books;
	}
	
	private String extractIsbn(JSONObject volumeInfo) {
		JSONArray identifiers = volumeInfo.optJSONArray("industryIdentifiers");
		if(identifiers != null) {
			for(int i = 0; i < identifiers.length(); i++) {
				JSONObject id = identifiers.getJSONObject(i);
				if("ISBN_13".equals(id.optString("type"))) {
					return id.optString("identifier");
				}
			}
		}
		return "";
	}
	

}

