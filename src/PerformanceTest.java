import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PerformanceTest {

    private BookManager manager;

    @BeforeEach
    public void setUp() {
        manager = new BookManager();
        manager.addBook(1, "해리포터", "Rolling", 2013);
        manager.addBook(2, "퍼시", "Jane", 2005);
        manager.addBook(3, "오리고기", "오리", 2024);
    }
    @Test
    public void testPerformance1() {
        System.out.println("testPerformance 시작");
        
        long startTime = 0;
        long endTime = 0;
        long durationSearch = 0;
        long durationSearchBs = 0;
        // 성능 테스트를 위한 키워드와 ID 설정
        String keyword = "해리포터";
        int id = 1;

        // searchBook 성능 테스트
        startTime = System.nanoTime();
        manager.searchBook(keyword);
        endTime = System.nanoTime();
        durationSearch = endTime - startTime;
        System.out.println("searchBook 걸린 시간: " + durationSearch + " 나노초");

        // search_bs 성능 테스트
        startTime = System.nanoTime();
        manager.search_bs(id);
        endTime = System.nanoTime();
        durationSearchBs = endTime - startTime;
        System.out.println("search_bs 걸린 시간: " + durationSearchBs + " 나노초");

        // 성능 비교 테스트
        assertTrue(durationSearch > 0, "searchBook 수행 시간 확인 필요");
        assertTrue(durationSearchBs > 0, "search_bs 수행 시간 확인 필요");


        System.out.println("testPerformance 종료");
    }

    @Test
    public void testPerformance3() {
        System.out.println("testPerformance3 시작");

        long startTime = 0;
        long endTime = 0;
        long durationSearch = 0;
        long durationSearchBs = 0;
        // 성능 테스트를 위한 키워드와 ID 설정
        String keyword = "오리고기";
        int id = 3;

        // searchBook 성능 테스트
        startTime = System.nanoTime();
        manager.searchBook(keyword);
        endTime = System.nanoTime();
        durationSearch = endTime - startTime;
        System.out.println("searchBook 걸린 시간: " + durationSearch + " 나노초");

        // search_bs 성능 테스트
        startTime = System.nanoTime();
        manager.search_bs(id);
        endTime = System.nanoTime();
        durationSearchBs = endTime - startTime;
        System.out.println("search_bs 걸린 시간: " + durationSearchBs + " 나노초");

        // 성능 비교 테스트
        assertTrue(durationSearch > 0, "searchBook 수행 시간 확인 필요");
        assertTrue(durationSearchBs > 0, "search_bs 수행 시간 확인 필요");

        System.out.println("testPerformance3 종료");
    }
    
    @Test
    public void testPerformance_search_bs_largeData() {
        System.out.println("testPerformance_search_bs_largeData 시작");
        
        long startTime = 0;
        long endTime = 0;
        long durationSearch = 0;
        long durationSearchBs = 0;

        // 대규모 데이터 추가
        for (int i = 100; i < 1000; i++) {
            manager.addBook(i, "Book" + i, "Author" + i, 2000 + i);
        }
        
        String keyword = "Book550"; // 검색할 키워드 설정
        int id = 550; // 검색할 ID 설정

        // searchBook 성능 테스트
        startTime = System.nanoTime();
        manager.searchBook(keyword);
        endTime = System.nanoTime();
        durationSearch = endTime - startTime;
        System.out.println("searchBook 걸린 시간: " + durationSearch + " 나노초");

        // search_bs 성능 테스트
        startTime = System.nanoTime();
        manager.search_bs(id);
        endTime = System.nanoTime();
        durationSearchBs = endTime - startTime;
        System.out.println("search_bs 걸린 시간: " + durationSearchBs + " 나노초");

        // 성능 비교 테스트
        assertTrue(durationSearch > 0, "searchBook 수행 시간 확인 필요");
        assertTrue(durationSearchBs > 0, "search_bs 수행 시간 확인 필요");
        
        System.out.println("testPerformance_search_bs_largeData 종료");
    }
}