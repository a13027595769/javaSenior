package com;

import com.bean.Article;
import com.bean.Book;
import com.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;
    @Autowired
    BookRepository bookRepository;

    @Test
    public void test02() throws Exception {
       /* Book book = new Book();
        book.setId(1);
        book.setAuthor("老树");
        book.setBookName("花乱开");
        Book index = bookRepository.index(book);*/
        List<Book> byBookNameLike = bookRepository.findByBookNameLike("乱");
        System.out.println(byBookNameLike);

    }

    @Test
    public void contextLoads() {
        //1.给ES中索引(保存)一个文档
        Article article = new Article();
        article.setId(1);
        article.setAuthor("张三");
        article.setTitle("好消息");
        article.setContent("hello world");
        Index build = new Index.Builder(article).index("mypack").type("news").build();
        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search() {
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search build = new Search.Builder(json).addIndex("mypack").addType("news").build();
        try {
            SearchResult execute = jestClient.execute(build);
            System.out.println(execute.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
