package board.board.controller;

import board.board.entity.BoardEntity;
import board.board.service.BoardService;
import board.board.entity.BoardFileEntity;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(description="Swagger2 for the BoardController")
public class BoardController {
	
	@Autowired
	private BoardService BoardService;
	
	
	@ApiOperation(value = "게시글 조희 페이지, Manager 권한의 Index 페이지")
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView BoardList(ModelMap model) throws Exception{
		ModelAndView mav = new ModelAndView("/board/BoardList");
		List<BoardEntity> list = BoardService.selectBoardList();
		mav.addObject("list", list);
		return mav;
	}
	
	
	@ApiOperation(value = "게시글 작성 페이지")
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String BoardWrite() throws Exception{
		return "/board/BoardWrite";
	}
	
    
	@ApiOperation(value = "게시글 작성 페이지")
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		BoardService.saveBoard(board, multipartHttpServletRequest);
		return "redirect:/board";
	}
	
	//@RequestMapping(value="/board/write", method=RequestMethod.POST)
	//public String writeBoard(BoardEntity board) throws Exception{
	//	BoardService.saveBoard(board);
	//	return "redirect:/board";
	//}
	
	
	@ApiOperation(value = "게시글 상세 페이지")
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.GET)
	public ModelAndView BoardDetail(@PathVariable("boardIdx") @ApiParam(value="게시글 번호") int boardIdx) throws Exception{
		ModelAndView mav = new ModelAndView("/board/BoardDetail");
		BoardEntity board = BoardService.selectBoardDetail(boardIdx);
		mav.addObject("board", board);
		return mav;
	}
	
	
	@ApiOperation(value = "게시글 상세 페이지")
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.PUT)
	public String saveBoard(BoardEntity board) throws Exception{
		BoardService.saveBoard(board, null);
		return "redirect:/board";
	}
	
	
	@ApiOperation(value = "게시글 상세 페이지")
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") @ApiParam(value="게시글 번호") int boardIdx) throws Exception{
		BoardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}
	
	
	@ApiOperation(value = "게시글 파일 첨부")
	@RequestMapping(value="/board/file", method=RequestMethod.GET)
	public void fileBoard(int boardIdx, int idx, HttpServletResponse response) throws Exception {
		BoardFileEntity file = BoardService.selectBoardFileInformation(boardIdx, idx); 
		byte[] files = FileUtils.readFileToByteArray(new File(file.getStoredFilePath()));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(files.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getOriginalFileName(),"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		response.getOutputStream().write(files);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}

