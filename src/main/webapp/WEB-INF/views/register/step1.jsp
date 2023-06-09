<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>회원가입 약관</title>
  <style>
    /* 모달 스타일 */
    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.4);
    }

    .modal-content {
      background-color: #fefefe;
      margin: 10% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
      max-width: 600px;
    }

    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
      cursor: pointer;
    }
  </style>
</head>
<body>
<!-- 모달 버튼 -->
<button onclick="openModal()">약관 보기</button>

<!-- 모달 창 -->
<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <h2>회원가입 약관</h2>
    <p>
      [편의점명] 회원가입 약관<br/><br/>

      제 1장 총칙<br/>

      제 1조 (목적)<br/>
      본 약관은 [편의점명] (이하 "회사"라 함)이 운영하는 온라인 회원 서비스를 이용하기 위한 회원의 권리, 의무, 기타 필요한 사항을 정하는 것을 목적으로 합니다.<br/><br/>

      제 2조 (약관의 효력 및 변경)<br/><br/>

      이 약관은 회원가입 시 회원에게 공지함으로써 효력을 발생합니다.<br/>
      회사는 필요한 경우 관련 법령에 위배되지 않는 범위에서 이 약관을 변경할 수 있으며, 변경된 약관은 회원에게 통지함으로써 효력을 발생합니다. 회원은 변경된 약관에 대해 동의하지 않을 경우 회원 탈퇴를 요청할 수 있습니다.<br/>
      제 3조 (회원의 의무)<br/><br/>

      회원은 본 약관을 준수하여 회사의 서비스를 이용해야 합니다.<br/>
      회원은 회원가입 시 제공한 개인정보의 정확성을 유지해야 하며, 변경 시에는 즉시 회사에 알려야 합니다.<br/>
      회원은 타인의 개인정보를 도용하거나 부정하게 이용해서는 안 됩니다.<br/>
      회원은 회사의 서비스를 이용하는 과정에서 다른 회원의 권리를 침해하거나 법령을 위반해서는 안 됩니다.<br/>
      제 4조 (회사의 의무)<br/><br/>

      회사는 회원의 개인정보를 보호하기 위해 최선의 노력을 다하며, 개인정보 처리에 대한 관련 법령을 준수합니다.<br/>
      회사는 회원에게 안정적이고 지속적인 서비스 제공을 위해 최선을 다합니다.<br/><br/>

    </p>
  </div>
</div>
<form action="step2" method="post">
  <label>
    <input type="checkbox" name="agree" value="true">
    약관 동의
  </label>
  <input type="submit" value="다음 단계" />
</form>

<script>
  // 모달 열기
  function openModal() {
    var modal = document.getElementById("modal");
    modal.style.display = "block";
  }

  // 모달 닫기
  function closeModal() {
    var modal = document.getElementById("modal");
    modal.style.display = "none";
  }
</script>
</body>
</html>
