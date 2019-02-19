package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.admin.ImageSlideAction;
import action.board.admin.BoardComment;
import action.board.admin.BoardDeleteAction;
import action.board.admin.BoardListView;
import action.board.admin.BoardModifyAction;
import action.board.admin.BoardModifyView;
import action.board.admin.BoardReply;
import action.board.admin.BoardView;
import action.board.admin.BoardWriteAction;
import action.board.admin._BoardReplyAction;
import action.board.admin._BoardReplyView;
import action.board.vue.admin.BoardDeleteActionVue;
import action.board.vue.admin.BoardListViewVue;
import action.board.vue.admin.BoardModifyActionVue;
import action.board.vue.admin.BoardViewVue;
import action.board.vue.admin.BoardWriteActionVue;
import action.file.admin.FileDeleteAction;
import action.file.admin.FileListView;
import action.file.admin.FileModifyAction;
import action.file.admin.FileModifyView;
import action.file.admin.FileView;
import action.file.admin.FileWriteAction;
import action.file.vue.admin.FileDeleteActionVue;
import action.file.vue.admin.FileListViewVue;
import action.file.vue.admin.FileModifyActionVue;
import action.file.vue.admin.FileViewVueVue;
import action.file.vue.admin.FileWriteActionVue;
import action.gallery.admin.GalleryDeleteAction;
import action.gallery.admin.GalleryListView;
import action.gallery.admin.GalleryModifyAction;
import action.gallery.admin.GalleryModifyView;
import action.gallery.admin.GalleryView;
import action.gallery.admin.GalleryWriteAction;
import action.gallery.vue.admin.GalleryDeleteActionVue;
import action.gallery.vue.admin.GalleryListViewVue;
import action.gallery.vue.admin.GalleryModifyActionVue;
import action.gallery.vue.admin.GalleryViewVue;
import action.gallery.vue.admin.GalleryWriteActionVue;
import action.member.admin.MemberChangePasswordAction;
import action.member.admin.MemberChangePasswordView;
import action.member.admin.MemberConfirmIdAction;
import action.member.admin.MemberFindIdAction;
import action.member.admin.MemberLoginAction;
import action.member.admin.MemberLoginActionVue;
import action.member.admin.MemberLogoutAction;
import action.member.admin.MemberModifyAction;
import action.member.admin.MemberRegisterAction;
import action.notice.admin.NoticeDeleteAction;
import action.notice.admin.NoticeListView;
import action.notice.admin.NoticeModifyAction;
import action.notice.admin.NoticeModifyView;
import action.notice.admin.NoticeView;
import action.notice.admin.NoticeWriteAction;
import action.notice.vue.admin.NoticeDeleteActionVue;
import action.notice.vue.admin.NoticeListViewVue;
import action.notice.vue.admin.NoticeModifyActionVue;
import action.notice.vue.admin.NoticeViewVue;
import action.notice.vue.admin.NoticeWriteActionVue;

/**
 * Servlet implementation class MainAdminController
 */
@WebServlet("/MainAdminController")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestURI = request.getRequestURI();
		System.out.println("[Controller.java] requestURI : " + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("[Controller.java] contextPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("[Controller.java] command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		RequestDispatcher dispatcher = null;
		
		if (command.equals("/admin/login.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/login.jsp");
			
		} else if (command.equals("/admin/login.action")) {
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/logout.action")) {
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/register.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/register.jsp");
			
		} else if (command.equals("/admin/register.action")) {
			action = new MemberRegisterAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/confirmId.action")) {
			action = new MemberConfirmIdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/find/id.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/find/id.jsp");
			
		} else if (command.equals("/admin/findId.action")) {
			action = new MemberFindIdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/find/password.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/find/password.jsp");
			
		} else if (command.equals("/admin/member/password/change.do")) {
			action = new MemberChangePasswordView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/member/password/change.action")) {
			action = new MemberChangePasswordAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/member/result.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/result.jsp");
		
		} else if (command.equals("/admin/member/modify.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/member/modify.jsp");
			
		} else if(command.equals("/admin/member/modify.action")) {
			action = new MemberModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/board/list.do")) {
			action = new BoardListView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/view.do")) {
			action = new BoardView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/reply.do")) {
			action = new _BoardReplyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/reply.action")) {
			action = new _BoardReplyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/write.do")) {
			request.setAttribute("smarteditor", true);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/board/write.jsp");
			
		} else if (command.equals("/admin/board/write.action")) {
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/modify.do")) {
			action = new BoardModifyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/modify.action")) {
			action = new BoardModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/delete.action")) {
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/comment.action")) {
			action = new BoardComment();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/board/comment_reply.action")) {
			action = new BoardReply();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/gallery/list.do")) {
			action = new GalleryListView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/gallery/view.do")) {
			action = new GalleryView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/gallery/write.do")) {
			request.setAttribute("smarteditor", true);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/gallery/write.jsp");
			
		} else if (command.equals("/admin/gallery/write.action")) {
			action = new GalleryWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/gallery/modify.do")) {
			action = new GalleryModifyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/gallery/modify.action")) {
			action = new GalleryModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/gallery/delete.action")) {
			action = new GalleryDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/file/list.do")) {
			action = new FileListView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/file/view.do")) {
			action = new FileView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/file/write.do")) {
			request.setAttribute("smarteditor", true);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/file/write.jsp");
			
		} else if (command.equals("/admin/file/write.action")) {
			action = new FileWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/file/modify.do")) {
			action = new FileModifyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/file/modify.action")) {
			action = new FileModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/file/delete.action")) {
			action = new FileDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/notice/list.do")) {
			action = new NoticeListView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/notice/view.do")) {
			action = new NoticeView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/notice/write.do")) {
			request.setAttribute("smarteditor", true);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/notice/write.jsp");
			
		} else if (command.equals("/admin/notice/write.action")) {
			action = new NoticeWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/notice/modify.do")) {
			action = new NoticeModifyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/notice/modify.action")) {
			action = new NoticeModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/notice/delete.action")) {
			action = new NoticeDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
		} else if(command.equals("/admin/youtube/list.do")) {
			action = new NoticeListView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/youtube/view.do")) {
			action = new NoticeView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/youtube/write.do")) {
			request.setAttribute("smarteditor", true);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/youtube/write.jsp");
			
		} else if (command.equals("/admin/youtube/write.action")) {
			action = new NoticeWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/youtube/modify.do")) {
			action = new NoticeModifyView();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/youtube/modify.action")) {
			action = new NoticeModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/youtube/delete.action")) {
			action = new NoticeDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
		} else if (command.equals("/admin/slide.do")) {
			action = new ImageSlideAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/slide.action")) {
			action = new ImageSlideAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/portfolio.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/portfolio/list.jsp");
			
		} else if (command.equals("/admin/story.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/html/story/list.jsp");
			
		}
		
		
		
		
		
		
		else if (command.equals("/admin/vuejs/login.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/login.jsp");
			
		} else if (command.equals("/admin/vuejs/login.action")) {
			action = new MemberLoginActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/logout.action")) {
			/*
			action = new MemberLogoutActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/register.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/register.jsp");
			
		} else if (command.equals("/admin/vuejs/register.action")) {
			/*
			action = new MemberRegisterActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/confirmId.action")) {
			/*
			action = new MemberConfirmIdActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/find/id.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/find/id.jsp");
			*/
			
		} else if (command.equals("/admin/vuejs/findId.action")) {
			/*
			action = new MemberFindIdActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/find/password.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/find/password.jsp");
			*/
			
		} else if (command.equals("/admin/vuejs/member/password/change.do")) {
			/*
			action = new MemberChangePasswordViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/member/password/change.action")) {
			/*
			action = new MemberChangePasswordActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/member/result.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/result.jsp");
			*/
		
		} else if (command.equals("/admin/vuejs/member/modify.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/member/modify.jsp");
			*/
			
		} else if(command.equals("/admin/vuejs/member/modify.action")) {
			/*
			action = new MemberModifyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if(command.equals("/admin/vuejs/board/list.do")) {
			action = new BoardListViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/board/view.do")) {
			action = new BoardViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/board/reply.do")) {
			/*
			action = new _BoardReplyViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/board/reply.action")) {
			/*
			action = new _BoardReplyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/board/write.action")) {
			action = new BoardWriteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/board/modify.action")) {
			action = new BoardModifyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/board/delete.action")) {
			action = new BoardDeleteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/admin/vuejs/board/comment.action")) {
			action = new BoardComment();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/board/comment_reply.action")) {
			action = new BoardReply();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/vuejs/gallery/list.do")) {
			action = new GalleryListViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/gallery/view.do")) {
			action = new GalleryViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/gallery/write.action")) {
			action = new GalleryWriteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/gallery/modify.action")) {
			action = new GalleryModifyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/gallery/delete.action")) {
			action = new GalleryDeleteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/vuejs/download.do")) {
			/*
			action = new Download();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if(command.equals("/admin/vuejs/download/list.do")) {
			action = new FileListViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/download/view.do")) {
			action = new FileViewVueVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/download/write.action")) {
			action = new FileWriteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/download/modify.action")) {
			action = new FileModifyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/download/delete.action")) {
			action = new FileDeleteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/vuejs/notice/list.do")) {
			action = new NoticeListViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/notice/view.do")) {
			action = new NoticeViewVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/notice/write.action")) {
			action = new NoticeWriteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/notice/modify.action")) {
			action = new NoticeModifyActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/notice/delete.action")) {
			action = new NoticeDeleteActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (command.equals("/admin/vuejs/slide.do")) {
			/*
			action = new ImageSlideActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/slide.action")) {
			/*
			action = new ImageSlideActionVue();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
		} else if (command.equals("/admin/vuejs/portfolio.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/portfolio/list.jsp");
			*/
			
		} else if (command.equals("/admin/vuejs/story.do")) {
			/*
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/vuejs/html/story/list.jsp");
			*/
			
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// System.out.println("[Controller.java] forward.isRedirect(): " + forward.isRedirect());
				
				response.sendRedirect(forward.getPath());
			} else {
				// System.out.println("[Controller.java] forward.isRedirect(): " + forward.isRedirect());
				
				dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
