package mayfifth99.twitter.admin.ui.query;

import mayfifth99.twitter.admin.ui.dto.posts.GetPostTableRequestDto;
import mayfifth99.twitter.admin.ui.dto.posts.GetPostTableResponseDto;
import mayfifth99.twitter.admin.ui.dto.GetTableListResponse;
import mayfifth99.twitter.admin.ui.dto.users.GetUserTableRequestDto;
import mayfifth99.twitter.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);
}
