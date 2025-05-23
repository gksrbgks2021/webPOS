<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 발주 페이지</title>
    <style>
        .container {
            display: flex;
            height: 100vh;
            font-family: Arial, sans-serif;
        }
        .left-section {
            width: 50%;
            padding: 20px;
            overflow-y: auto;
            border-right: 1px solid #ccc;
        }
        .right-section {
            width: 50%;
            padding: 20px;
            background-color: #f9f9f9;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 10px;
        }
        .product-grid button {
            width: 100%;
            aspect-ratio: 1/1;
            background-color: #0078D7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .product-grid button:hover {
            background-color: #005A9E;
        }
        .product-grid p {
            text-align: center;
            margin: 5px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .summary {
            font-size: 1.2em;
            margin-top: 20px;
        }
        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #0078D7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s;
        }
        .submit-btn:hover {
            background-color: #005A9E;
        }
    </style>
</head>
<body>

<script>
    let orderItems = []; // 선택한 상품 정보를 저장할 배열

    function addProduct(name, costPrice) {
        let table = document.getElementById("orderTable");//테이블을 가져온다.
        let rows = table.rows;//태이블 행 을 가져온다.

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

                let priceCell = row.cells[2];
                let price = parseInt(priceCell.innerHTML);
                priceCell.innerHTML = (quantity + 1) * costPrice;
                // updateTotalPrice();
                updateSummary();

                return;

            }
        }

        // 선택한 상품을 테이블에 추가
        let row = table.insertRow();
        let nameCell = row.insertCell(0);
        let quantityCell = row.insertCell(1);
        let priceCell = row.insertCell(2);
        let deleteCell = row.insertCell(3); // 삭제 버튼을 위한 셀 추가
        nameCell.innerHTML = name;
        quantityCell.innerHTML = "<input type='number' name='quantity' min='1' value='1' onchange='updateTotalPrice()'>";
        priceCell.innerHTML = costPrice;
        // 삭제 버튼 추가
        deleteCell.innerHTML = "<button onclick='deleteRow(this, \"" + name + "\")'>X</button>";

        updateTotalPrice(); // 총 가격 업데이트

        //서버로 보낼 정보들이다.
        orderItems.push({
            name: name,
            costPrice: costPrice,
            quantity: 1
        });
    }

    function getTotalPrice(){
        return document.getElementById("totalPrice").innerText.replace('+','');
    }

    function getTotalPrice() {
        return document.getElementById("totalPrice").innerText.replace('+', '');
    }

    function updateSummary() {
        let table = document.getElementById("orderTable");
        let rows = table.rows;
        let totalPrice = 0;
        let totalQuantity = 0;

        // 총 가격과 총 수량 계산
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            let quantityInput = row.cells[1].querySelector("input[type='number']");
            let quantity = parseInt(quantityInput.value);
            let price = parseInt(row.cells[2].innerHTML);
            totalPrice += price;
            totalQuantity += quantity;
        }

        document.getElementById("totalQuantity").innerHTML = totalQuantity;
        document.getElementById("totalPrice").innerHTML = totalPrice;
    }

    function updateTotalPrice() {
        let table = document.getElementById("orderTable");
        let rows = table.rows;
        let totalPrice = 0;

        // 선택한 상품의 개수와 주문할 가격을 곱하여 총 가격 계산
        for (let i = 1; i < rows.length; i++) {
            let row = rows[i];
            let quantityInput = row.cells[1].querySelector("input[type='number']");
            let price = parseInt(row.cells[2].innerHTML); // 주문할 가격
            totalPrice += price;
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
        form.action = "/sale/${storeName}/+"+getTotalPrice()+"/submit";

        // 상품 정보를 폼에 추가
        //<input tag> 생성해서 리퀘스트 추가합니다.
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

            let priceInput = document.createElement("input");
            priceInput.type = "hidden";
            priceInput.name = "price" + i;
            priceInput.value = orderItem.costPrice * orderItem.quantity;;
            form.appendChild(priceInput);
        }

        // 폼을 현재 페이지에 추가하고 전송
        document.body.appendChild(form);
        form.submit();
    }

    function deleteRow(btn, name) {
        // 테이블에서 행 삭제
        let row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);

        // orderItems 배열에서 해당 항목 삭제
        for (let i = 0; i < orderItems.length; i++) {
            if (orderItems[i].name === name) {
                orderItems.splice(i, 1);
                break;
            }
        }

        // 총 가격 업데이트
        // updateTotalPrice();
        updateSummary();
    }


</script>

<div class="container">
    <div class="left-section">
        <h2>[판매 상품 조회]</h2>
        <p>판매할 상품을 선택해주세요.</p><br/>
        <c:choose>
            <c:when test="${empty productList}">
                <p>상품 정보가 없습니다!</p>
            </c:when>
            <c:otherwise>
                <div class="product-grid">
                    <c:forEach items="${productList}" var="product">
                        <div>
                            <button type="button" onclick="addProduct('${product.name}', ${product.costPrice})">${product.name}</button>
                            <p>${product.costPrice}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="right-section">
        <h2>주문 내역</h2>
        <table id="orderTable">
            <tr>
                <th>상품 이름</th>
                <th>상품 개수</th>
                <th>총 가격</th>
                <th>삭제</th>
            </tr>
        </table>
        <div class="summary">
            <p>총 수량: <span id="totalQuantity">0</span></p>
            <p>총 합계: <span id="totalPrice">0</span></p>
        </div>
        <button class="submit-btn" type="button" onclick="submitOrder()">주문하기</button>
    </div>
</div>


</body>
</html>
