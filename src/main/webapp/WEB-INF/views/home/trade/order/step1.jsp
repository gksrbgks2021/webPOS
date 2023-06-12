<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 발주 페이지</title>
</head>
<body>

<script>
    let orderItems = []; // 선택한 상품 정보를 저장할 배열

    // 선택한 상품을 테이블에 추가하거나 개수를 증가시키는 함수
    function addProduct(name, netPrice) {
        let table = document.getElementById("orderTable");
        let rows = table.rows;

        // 이미 선택한 상품인지 확인
        let isExist = false;
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            if (row.cells[0].innerHTML === name) {
                let quantityInput = row.cells[1].querySelector("input[type='number']");
                let quantity = parseInt(quantityInput.value);
                quantityInput.value = quantity + 1;

                // 선택한 상품 정보의 수량을 증가시킴
                for (let j = 0; j < orderItems.length; j++) {
                    if (orderItems[j].name === name) {
                        orderItems[j].quantity = quantity + 1;
                        break;
                    }
                }

                // 주문할 가격 업데이트
                let priceCell = row.cells[2];
                let price = parseInt(priceCell.innerHTML);
                priceCell.innerHTML = price + netPrice;

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
            netPrice: netPrice,
            quantity: 1
        });
    }

    // 총 가격을 계산하고 보여주는 함수
    function updateTotalPrice() {
        let table = document.getElementById("orderTable");
        let rows = table.rows;
        let totalPrice = 0;

        // 선택한 상품의 개수와 주문할 가격을 곱하여 총 가격 계산
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            let quantityInput = row.cells[1].querySelector("input[type='number']");
            let quantity = parseInt(quantityInput.value);
            let price = parseInt(row.cells[2].innerHTML); // 주문할 가격
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

        // 주문 정보를 폼으로 생성
        let form = document.createElement("form");
        form.method = "post";
        form.action = "/order/submit";

        // 상품 정보를 폼에 추가
        for (let i = 0; i < orderItems.length; i++) {
            let orderItem = orderItems[i];

            let nameInput = document.createElement("input");
            nameInput.type = "hidden";
            nameInput.name = "name" + i;
            nameInput.value = orderItem.name;
            form.appendChild(nameInput);

            let quantityInput = document.createElement("input");
            quantityInput.type = "hidden";
            quantityInput.name = "quantity" + i;
            quantityInput.value = orderItem.quantity;
            form.appendChild(quantityInput);
        }

        // 폼을 현재 페이지에 추가하고 전송
        document.body.appendChild(form);
        form.submit();
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
    <form id="orderForm" method="post" action="/order/submit">
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
    </form>
</div>

</body>
</html>
