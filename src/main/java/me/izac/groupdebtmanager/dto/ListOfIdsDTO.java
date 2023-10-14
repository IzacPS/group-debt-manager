package me.izac.groupdebtmanager.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListOfIdsDTO {
    List<Long> ids;
}
