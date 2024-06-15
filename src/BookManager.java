import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class Book {
	private int id;
	private String title;
	private String author;
	private int year;
	
	public Book(int id, String title, String author, int year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString() {
		return "Book{id: \'" + id + "\', 제목: \'" + title 
				+ "\', 저자: \'" + author + "\', 출판년도: " + year + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book target = (Book) obj;
			return target.getId() == id &&
					target.getTitle().equals(title) &&
					target.getAuthor().equals(author) &&
					target.getYear() == year;
		}
		return false;
	}
}

public class BookManager {
    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }

    // 책 추가 메서드
    public int addBook(int id, String title, String author, int year) {
    	Book book = new Book(id, title, author, year);
		for (Book b: books) {
			if (b.getId() == book.getId()) {
				System.out.println("해당 ID(" + book.getId() + ")는 이미 존재합니다.");
				return 1;
			}
		}
		books.add(book);
        Collections.sort(books, Comparator.comparingInt(Book::getId)); // Add 후 데이터를 정렬
		System.out.println(book.toString() + "도서가 추가되었습니다.");
		return 0;
	}

    // 제목 또는 저자로 책 검색 메서드
    public List<Book> searchBook(String keyword) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                foundBooks.add(book);
            }
        }
        if (foundBooks.isEmpty()) {
            System.out.println("검색된 도서가 없습니다.");
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
        return foundBooks;
    }

    // ID로 책 삭제 메서드
    public void removeBook(int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                System.out.println("BOOK{id: '" + id + "', 제목: '" + book.getTitle() + "', 저자: '" + book.getAuthor() + "', 출판년도: " + book.getYear() + "} 도서를 삭제하였습니다.");
                return;
            }
        }
        System.out.println("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
    }
    
    // 이진 탐색 메서드
    public Book search_bs(int id) {
        int left = 0;
        int right = books.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            Book midBook = books.get(mid);

            if (midBook.getId() == id) {
                System.out.println(midBook.toString() + "도서를 찾았습니다.");
                return midBook;
            } else if (midBook.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
        return null;
    }
   
    // 성능 테스트 메서드
    public long performance_test_search(String keyword) {
        long startTime = System.nanoTime();
        searchBook(keyword);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long performance_test_search_bs(int id) {
        long startTime = System.nanoTime();
        search_bs(id);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}