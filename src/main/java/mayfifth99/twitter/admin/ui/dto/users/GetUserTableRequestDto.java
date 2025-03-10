package mayfifth99.twitter.admin.ui.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayfifth99.twitter.common.domain.Pageable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetUserTableRequestDto extends Pageable {

    private String name;
}
