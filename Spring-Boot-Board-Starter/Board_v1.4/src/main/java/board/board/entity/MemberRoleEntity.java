package board.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "t_member_roles")
@EqualsAndHashCode(of = "fno")
@ToString
@ApiModel(value="BoardEntity : �α��� ���� ��ƼƼ", description="�α��� ���� ��ƼƼ")
public class MemberRoleEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value="�α��� ���� �⺻Ű")
  private Long fno;
  
  @ApiModelProperty(value="�α��� ���� �̸�")
  private String roleName;
}