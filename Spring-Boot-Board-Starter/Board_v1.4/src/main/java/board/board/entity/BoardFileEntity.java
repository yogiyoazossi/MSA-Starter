package board.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="t_file")
@NoArgsConstructor
@Data
@ApiModel(value="BoardEntity : �Խ��� ����÷�� ��ƼƼ", description="�Խ��� ����÷�� ��ƼƼ")
public class BoardFileEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(value="���� ��ȣ")
	private int idx;
	
	@ApiModelProperty(value="���� ���� �̸�")
	@Column(nullable=false)
	private String originalFileName;
	
	@ApiModelProperty(value="���� ���� ���")
	@Column(nullable=false)
	private String storedFilePath;
	
	@ApiModelProperty(value="���� ũ��")
	@Column(nullable=false)
	private long fileSize;
	
	@ApiModelProperty(value="���� ���δ�")
	@Column(nullable=false)
	private String creatorId;
	
	@ApiModelProperty(value="���� ���� ��¥")
	@Column(nullable=false)
	private LocalDateTime createdDatetime = LocalDateTime.now();
	
	@ApiModelProperty(value="���� ������")
	private String updaterId;
	
	@ApiModelProperty(value="���� ���� ��¥")
	private LocalDateTime updatedDatetime;
}
