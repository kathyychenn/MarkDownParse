import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testOriginalFile() throws IOException {
        Path fileName = Path.of("break0.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testBreakFile1() throws IOException {
        Path fileName = Path.of("break1.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testBreakFile2() throws IOException {
        Path fileName = Path.of("break2.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("https://something().com"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testBreakFile3() throws IOException {
        Path fileName = Path.of("break3.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of(), MarkdownParse.getLinks(contents));
    }

    @Test
    public void snippet1Test() throws IOException{
        Path fileName = Path.of("snippet1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("60google.com","google.com","ucsd.edu"), links);
    }

    @Test
    public void snippet2Test() throws IOException{
        Path fileName = Path.of("snippet2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("a.com", "a.com(())", "example.com"), links);
    }
    
    @Test
    public void snippet3Test() throws IOException{
        Path fileName = Path.of("snippet3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"), links);
    }
    
}