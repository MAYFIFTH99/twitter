package mayfifth99.twitter.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() throws Exception {
        //given
        String content = "12345";

        //when
        PostContent postContent = new PostContent(content);

        //then
        Assertions.assertEquals(postContent.getContent(), content);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() throws Exception {
        //given
        String over = "a".repeat(1001);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(over));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentNullAndEmpty_whenCreated_thenThrowError(String nullAndEmpty) throws Exception {
        //when & then
        assertThrows( IllegalArgumentException
                .class, () -> new PostContent(nullAndEmpty));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnText(){
        //given
        String content = "12345";
        PostContent postContent = new PostContent(content);
        String updatedContent = "54321";

        //when
        postContent.updateContent(updatedContent);

        //then
        Assertions.assertEquals(postContent.getContent(), updatedContent);
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError(){
        //given
        String content = "12345";
        PostContent postContent = new PostContent(content);
        String over = "a".repeat(1001);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(over));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentNullAndEmpty_whenUpdated_thenThrowError(String nullAndEmpty){
        //given
        String content = "12345";
        PostContent postContent = new PostContent(content);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(nullAndEmpty));
    }

    

}