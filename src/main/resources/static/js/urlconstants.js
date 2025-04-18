(function() {
    window.API_URLS = {
        MANAGE_MEMBER: '/admin/actionName/<%=UrlParamAction.MANAGE_MEMBER%>?format=json',
        MANAGE_PRODUCT: '/admin/actionName/<%=UrlParamAction.MANAGE_PRODUCT%>',
        DELETE_PRODUCT: '/admin/actionName/<%=UrlParamAction.DELETE_PRODUCT%>'
    };
})();

/*
사용 예시:
import API_URLS from './urlconstants.js';

// 회원 목록 조회
fetch(API_URLS.MEMBER.LIST)
    .then(response => response.json())
    .then(data => console.log(data));

// 상품 생성
fetch(API_URLS.PRODUCT.CREATE, {
    method: 'POST',
    body: JSON.stringify(productData)
});
*/
