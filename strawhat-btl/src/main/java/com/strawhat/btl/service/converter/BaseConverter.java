package com.strawhat.btl.service.converter;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public interface BaseConverter<DTO, DO> {

    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    DO toDO(DTO dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    DTO toDTO(DO entity);

    /**
     * DTO集合转Entity集合
     * @param list /
     * @return /
     */
    List<DO> toDO(List<DTO> list);

    /**
     * Entity集合转DTO集合
     * @param list /
     * @return /
     */
    List<DTO> toDTO(List<DO> list);


    /**
     * 转换页面对象
     * @param page
     * @return
     */
    default Page<DTO> toDTO(Page<DO> page){
        Function<DO,DTO> convert = dict -> this.toDTO(dict);
        return page.map(convert);
    }



}
