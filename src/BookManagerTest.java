import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BookManagerTest {

    private BookManager manager;

    @BeforeEach
    public void setUp() {
        manager = new BookManager();
    }
    @Test
    public void testAddBook() {
        System.out.println("testAddBook 시작");

        // 책 추가 테스트
        int result = manager.addBook(1, "해리포터", "Rolling", 2013);
        assertEquals(0, result);  // 책이 성공적으로 추가되면 0을 반환
        if (result == 0) {
        	System.out.println("책이 성공적으로 추가되었습니다.");
        }

        result = manager.addBook(1, "퍼시", "Jane", 2021);
        assertEquals(1, result);  // 동일한 ID로 추가 시도시 1을 반환
        if (result == 1) {
        	System.out.println("중복 책 추가가 거절되었습니다.");
        }

        System.out.println("testAddBook 종료");
    }
    
    @Test
    public void testSearchBook() {
        System.out.println("testSearchBook 시작");

        // 초기 상태에서는 검색 결과가 없음
        List<Book> foundBooks = manager.searchBook("해리포터");
        assertEquals(0, foundBooks.size());
        System.out.println("검색 결과: " + foundBooks);

        // 책을 추가하고 검색 테스트
        manager.addBook(1, "해리포터", "Rolling", 2013);
        manager.addBook(2, "퍼시", "Jane", 2005);

        foundBooks = manager.searchBook("해리포터");
        assertEquals(1, foundBooks.size());
        assertEquals("Rolling", foundBooks.get(0).getAuthor());
        System.out.println("검색된 책: " + foundBooks);

        foundBooks = manager.searchBook("Jane");
        assertEquals(1, foundBooks.size());
        assertEquals("퍼시", foundBooks.get(0).getTitle());
        System.out.println("검색된 책: " + foundBooks);

        System.out.println("testSearchBook 종료");
    }

    @Test
    public void testRemoveBook() {
        System.out.println("testRemoveBook 시작");

        // 책 삭제 테스트
        manager.addBook(1, "해리포터", "Rolling", 2013);
        manager.addBook(2, "퍼시", "Jane", 2005);

        // 책을 성공적으로 삭제할 경우
        manager.removeBook(1);
        List<Book> foundBooks = manager.searchBook("해리포터");
        assertEquals(0, foundBooks.size());
        System.out.println("삭제 후 검색 결과: " + foundBooks);

        // 존재하지 않는 ID로 삭제 시도할 경우
        manager.removeBook(1);
        System.out.println("존재하지 않는 ID로의 삭제가 거절되었습니다.");

        System.out.println("testRemoveBook 종료");
    }
}
