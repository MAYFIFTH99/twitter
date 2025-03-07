package mayfifth99.twitter.post.ui.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetContentResponseDto {

    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private int likeCount;
    private boolean isLikedByMe;

}
