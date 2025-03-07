package mayfifth99.twitter.post.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // 부모 클래스의 생성자를 모두 포함한 생성자를 만들어주는 어노테이션
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostContentDto extends GetContentResponseDto{
    private int commentCount;

}
