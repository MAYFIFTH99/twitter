package mayfifth99.twitter.post.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;

@SuperBuilder // 부모 클래스의 생성자를 모두 포함한 생성자를 만들어주는 어노테이션
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostContentResponseDto extends GetContentResponseDto{
    private int commentCount;

    public static GetPostContentResponseDto from(PostEntity postEntity) {
        return GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .userId(postEntity.getAuthor().getId())
                .userName(postEntity.getAuthor().getName())
                .content(postEntity.getContent())
                .commentCount(postEntity.getCommentCount())
                .build();
    }
}
