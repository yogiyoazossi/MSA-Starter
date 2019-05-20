package board.board.entity;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import board.board.entity.BoardFileEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="t_board")
@NoArgsConstructor
@Data
@ApiModel(value="BoardEntity : �Խ��� ��ƼƼ", description="�Խ��� ��ƼƼ")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(value="�Խñ� ��ȣ")
	//private int boardIdx;
	private Integer boardIdx;

	@ApiModelProperty(value="�Խñ� �̸�")
	@Column(nullable=false)
	private String title;
	
	@ApiModelProperty(value="�Խñ� ����")
	@Column(nullable=false)
	private String contents;
	
	@ApiModelProperty(value="�Խñ� ��ȸ��")
	@Column(nullable=false)
	private int hitCnt = 0;
	
	@ApiModelProperty(value="�Խñ� �ۼ���")
	@Column(nullable=false)
	private String creatorId;
	
	@ApiModelProperty(value="�Խñ� ���� ��¥")
	@Column(nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createdDatetime = LocalDateTime.now();
	
	@ApiModelProperty(value="�Խñ� ������")
	private String updaterId;
	
	@ApiModelProperty(value="�Խñ� ���� ��¥")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updatedDatetime;
	
	@ApiModelProperty(value="�Խñ� ����÷��")
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="board_idx")
	private Collection<BoardFileEntity> fileList;
}
