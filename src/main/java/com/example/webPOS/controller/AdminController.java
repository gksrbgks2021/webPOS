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
    public String showAdminPage(
            @PathVariable("action") String action, Model model,
            HttpServletRequest request) throws Exception {

        if (action.equals(UrlParamAction.MANAGE_MEMBER)) {
            model.addAttribute("userList", memberService.showMember());
            return "home/admin/manageUser";
        } else if (action.equals(UrlParamAction.MANAGE_PRODUCT)) {//상품등록
            return "home/admin/enrollProduct";
        }else if (action.equals(UrlParamAction.DELETE_PRODUCT)) {
            model.addAttribute("productList", productMangeService.findAll());
            return "home/admin/showProducts";
        }
        return "home/main";
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
            productMangeService.deleteByProductId(Long.parseLong(request.getParameter("productId")));
            model.addAttribute("infoMsg", "삭제 성공했습니다");
            return "home/admin/success";
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


