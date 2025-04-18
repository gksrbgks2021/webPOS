
$(document).ready(function() {
    $('.nav-link[data-url]').click(function(e) {
        e.preventDefault();
        var url = $(this).data('url');
        
        var msg = $(this).text();
        $('#contentArea').html('<p>로딩 중...</p>');
        $.ajax({
            url: url,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                var html = '<h2>' + msg + '</h2><table class="member-table table table-striped table-hover">\
                            <tr class="table-header"><th>ID</th><th>이메일</th>\
                            <th>이름</th><th>역할</th><th>가입일</th><th>작업</th></tr>'; // Added "작업" header
                data.forEach(function(user) {
                    html += '<tr class="table-row">\
                                <td class="table-cell">' + user.id + '</td>\
                                <td class="table-cell">' + user.email + '</td>\
                                <td class="table-cell">' + user.name + '</td>\
                                <td class="table-cell">' + user.role + '</td>\
                                <td class="table-cell">' + user.regdate + '</td>\
                                <td class="table-cell">\
                                    <button class="btn btn-primary btn-sm edit-btn" data-id="' + user.id + '">\
                                        <i class="bi bi-pencil"></i> 수정\
                                    </button>\
                                    <button class="btn btn-danger btn-sm delete-btn" data-id="' + user.id + '">\
                                        <i class="bi bi-trash"></i> 삭제\
                                    </button>\
                                </td>\
                            </tr>';
                });
                html += '</table>';
                $('#contentArea').html(html);
            },
            error: function() {
                $('#contentArea').html('<p style="color:red;">데이터를 불러오지 못했습니다.</p>');
            }
        });
    });
});;