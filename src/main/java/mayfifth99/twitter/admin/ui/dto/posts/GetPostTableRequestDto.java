package mayfifth99.twitter.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayfifth99.twitter.common.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableRequestDto extends Pageable {

    private Long postId;
}
