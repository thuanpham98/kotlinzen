package vn.thuanpm.kotlinzen.applications

import vn.thuanpm.kotlinzen.domains.dtos.HumanDTO
import vn.thuanpm.kotlinzen.domains.models.HumanEntity

fun mapHumanEntity(dto:HumanDTO?): HumanEntity {
  if(dto===null){
    throw  NullPointerException("Human DTO is null");
  }
  val human= HumanEntity();
  human.name=dto.name;
  human.id=dto.id;
  human.completed= dto.completed == true;
  return human;
}

fun mapHumanDTO(entity:HumanEntity?):HumanDTO{
  if(entity===null){
    throw  NullPointerException("Human Entity is null");
  }
  val human= HumanDTO(entity.id,entity.name,entity.completed)
  return human;
}
