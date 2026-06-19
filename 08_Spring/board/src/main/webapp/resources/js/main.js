// 삭제 버튼 클릭 핸들러
document.querySelector('.delete').onclick = function() {
    if(!confirm('정말 삭제하시겠습니까?')) return;
    document.getElementById("deleteForm").submit();
}