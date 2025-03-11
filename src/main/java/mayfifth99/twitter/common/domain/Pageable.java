package mayfifth99.twitter.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    private int pageIndex;
    private int pageSize;

    public Pageable() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public Pageable(int pageIndex, int pageSize) {
        if(pageIndex < 1){
            throw new IllegalArgumentException("페이지 인덱스는 1보다 작을 수 없습니다.");
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    public int getOffset(){
        return (pageIndex - 1) * pageSize;
    }

    public int getLimit(){
        return pageSize;
    }

}
