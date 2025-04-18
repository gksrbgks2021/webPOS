package com.example.webPOS.controller;

import com.example.webPOS.constants.SessionConstants;
import com.example.webPOS.constants.urlpath.UrlParamAction;
import com.example.webPOS.dto.Member;
import com.example.webPOS.dto.Product;
import com.example.webPOS.service.MemberService;
import com.example.webPOS.service.ProductMangeService;
import jakarta.servlet.http.HttpServletRequest;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class AdminController {

    ProductMangeService productMangeService;

    MemberService memberService;

    @Autowired
    public void setProductMangeService(ProductMangeService productMangeService) {
        this.productMangeService = productMangeService;
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
//
//<form action="admin/productRegistration" method="post">
//    <button type="submit">상품 등록</button>
//</form>
//
//<form action="admin/userManagement" method="post">
//    <button type="submit">회원 관리</button>
//</form>

    @GetMapping("/admin/actionName/{action}")
    @ResponseBody
    public Object showAdminPage(
            @PathVariable("action") String action,
            Model model,
            @RequestParam(value = "format", defaultValue = "jsp") String format,
            HttpServletRequest request) throws Exception {
        // 데이터 조회
        Object data = null;
        String viewName = "home/main";

        if (action.equals(UrlParamAction.MANAGE_MEMBER)) {
            data = memberService.showMember();
            model.addAttribute("userList", data);
            viewName = "home/admin/manageUser";
        } else if (action.equals(UrlParamAction.MANAGE_PRODUCT)) {
            data = new HashMap<String, String>() {{ put("view", "enrollProduct"); }};
            viewName = "home/admin/enrollProduct";
        } else if (action.equals(UrlParamAction.DELETE_PRODUCT)) {
            data = productMangeService.findAll();
            model.addAttribute("productList", data);
            viewName = "home/admin/showProducts";
        }
        // format에 따라 반환
        if ("json".equals(format)) {
            return data; // JSON으로 직렬화
        } else {
            return viewName; // JSP 뷰 이름
        }
    }

    @PostMapping("admin/actionName/processName/{process}")
    public String handleProcess(
            @PathVariable("process") String action,
            HttpServletRequest request, Model model) throws Exception {

        if (action.equals(UrlParamAction.PROCESS_ENROLL_PRODUCT)) {
            String productID = request.getParameter("productID");
            String netprice = request.getParameter("netprice");
            String costprice = request.getParameter("costprice");
            String name = request.getParameter("name");

            Product product = new Product(Long.parseLong(netprice),
                    Long.parseLong(costprice),
                    name);
            try {
                productMangeService.save(product);
                model.addAttribute("infoMsg", "상품 등록에 성공하였습니다");
                return "home/admin/success";
            } catch (Exception e) {
                model.addAttribute("errorMsg", "상품 등록에 실패하였습니다");
                return "home/admin/error";
            }
        } else if (action.equals(UrlParamAction.PROCESS_DELETE_PRODUCT)) {
            try {
                productMangeService.deleteByProductId(Long.parseLong(request.getParameter("productId")));
                model.addAttribute("infoMsg", "삭제 성공했습니다");
                return "home/admin/success";

            } catch (Exception e) {
                model.addAttribute("errorMsg", "상품이 다른 편의점에 존재함으로 삭제할 수 없습니다");
                return "home/admin/error";
            }

        } else if (action.equals(UrlParamAction.PROCESS_DELETE_MEMBER)) {
            System.out.println("==삭제 메소드 들어옴==");
            memberService.deleteById(Long.parseLong(request.getParameter("id")));
            model.addAttribute("infoMsg", "삭제 성공했습니다");
            System.out.println("==서버가 응답 뿌림.==");
            return "home/admin/success";
        }

        return "home/main";
    }
    @PostMapping("admin/userManagement")
    public String manageUser1(Model model) {

        model.addAttribute("userList", memberService.showMember());

        return "home/admin/manageUser";
    }

}


