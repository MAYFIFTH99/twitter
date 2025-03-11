package mayfifth99.twitter.admin.ui.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetTableListResponse<T> {

    private int totalCount;
    private List<T> tableData;
}
