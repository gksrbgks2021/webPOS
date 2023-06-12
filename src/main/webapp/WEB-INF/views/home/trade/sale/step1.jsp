<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 판매 페이지</title>
</head>
<body>

<script>
    let orderItems = []; // 선택한 상품 정보를 저장할 배열

    // 선택한 상품을 테이블에 추가하거나 개수를 증가시키는 함수
    function addProduct(name, netPrice) {
        let table = document.getElementById("orderTable");
        let rows = table.rows;

        // 이미 선택한 상품인지 확인
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            if (row.cells[0].innerHTML === name) {
                let quantityInput = row.cells[1].querySelector("input[type='number']");
                let quantity = parseInt(quantityInput.value);
                quantityInput.value = quantity + 1;
                updateTotalPrice(); // 총 가격 업데이트
                return;
            }
        }

        // 선택한 상품을 테이블에 추가
        let row = table.insertRow();
        let nameCell = row.insertCell(0);
        let quantityCell = row.insertCell(1);
        let priceCell = row.insertCell(2);
        nameCell.innerHTML = name;
        quantityCell.innerHTML = "<input type='number' name='quantity' min='1' value='1' onchange='updateTotalPrice()'>";
        priceCell.innerHTML = netPrice;

        updateTotalPrice(); // 총 가격 업데이트

        // 선택한 상품 정보를 배열에 추가
        orderItems.push({
            name: name,
            netPrice: netPrice
        });
    }

    // 총 가격을 계산하고 보여주는 함수
    function updateTotalPrice() {
        let table = document.getElementById("orderTable");
        let rows = table.rows;
        let totalPrice = 0;

        // 선택한 상품의 개수와 가격을 곱하여 총 가격 계산
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            let quantityInput = row.cells[1].querySelector("input[type='number']");
            let quantity = parseInt(quantityInput.value);
            let price = parseInt(row.cells[2].innerHTML);
            totalPrice += quantity * price;
        }

        let totalPriceElement = document.getElementById("totalPrice");
        totalPriceElement.innerHTML = totalPrice;
    }

    // 주문을 서버로 전송하는 함수
    function submitOrder() {
        if (orderItems.length === 0) {
            alert("주문할 상품이 없습니다!");
            return;
        }

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/home", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("주문이 성공적으로 처리되었습니다!");
                // 서버 응답에 따른 처리 코드 작성
            }
        };
        xhr.send(JSON.stringify(orderItems));
    }
    // 모달 창 표시 함수
    function showModal(message) {
        // 모달 창 요소 선택
        let modal = document.getElementById("myModal");

        // 모달 내용 업데이트
        let modalMessage = document.getElementById("modalMessage");
        modalMessage.textContent = message;

        // 모달 창 표시
        modal.style.display = "block";
    }

    // 주문을 서버로 전송하는 함수
    function submitOrder() {
        if (orderItems.length === 0) {
            alert("주문할 상품이 없습니다!");
            return;
        }

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "your_submit_url_here", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 서버 응답 처리
                    let response = JSON.parse(xhr.responseText);
                    showModal(response.message);
                } else {
                    // 서버 오류 처리
                    showModal("주문 처리 중 오류가 발생했습니다.");
                }
            }
        };
        xhr.send(JSON.stringify(orderItems));
    }

    // 주문 계속하기 버튼 클릭 시 페이지 리로드
    function continueOrder() {
        location.reload();
    }

    // 주문 그만하기 버튼 클릭 시 home 경로로 이동
    function cancelOrder() {
        window.location.href = "/home";
    }

</script>

<h2>[주문할 상품 조회]</h2>
<p>상품 발주는 순원가(Net Price)로 표기됩니다.</p><br/>
<div style="width:500px;">
    <c:choose>
        <c:when test="${empty productList}">
            <p>상품 정보가 없습니다!</p>
        </c:when>
        <c:otherwise>
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); grid-gap: 10px;">
                <c:forEach items="${productList}" var="product">
                    <div>
                        <button type="button" onclick="addProduct('${product.name}', ${product.netPrice})"
                                style="width: 120px; aspect-ratio: 1/1;">${product.name}</button>
                        <p>${product.netPrice}</p>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

    <br/>
    <table id="orderTable">
        <tr>
            <th>상품 이름</th>
            <th>상품 개수</th>
            <th>주문할 가격</th>
        </tr>
    </table>
    <br/>

    <p>총 가격: <span id="totalPrice">0</span></p>
    <br/>
    <button type="button" onclick="submitOrder()">주문하기</button>
</div>

<!-- 모달 창 -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <p id="modalMessage"></p>
        <button onclick="continueOrder()">주문 계속하기</button>
        <button onclick="cancelOrder()">주문 그만하기</button>
    </div>
</div>


</body>
</html>

</body>
</html>
