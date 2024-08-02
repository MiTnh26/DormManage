package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@Builder
@ToString
public class FloorAndFreeBed {
    private Set<Integer> floors;
    private long freeBed;
}
