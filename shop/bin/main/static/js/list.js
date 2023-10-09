$(function(){
    $.ajax({
      url:'/cartlist_api',
      success:function(data) {
        let timeList = [];
        let date = document.querySelector('select');
        $(date).append('<option value="">날짜선택</option>');
          for (let i = 0; i < data.length; i++) {
          let row = data[i];
          let tr = $('<tr></tr>');
          tr.append('<td>' + row['idx'] + '</td>');
          tr.append('<td>' + row['get_time'] + '</td>');
          tr.append('<td>' + row['product'] + '</td>');
          tr.append('<td>' + row['price'] + '</td>');
          tr.append('<td>' + '<button id="button" onclick="del('+row['idx']+')">삭제</button>' + '</td>');
          $('.list tbody').append(tr);
          tr.css('background-color', row['code']);
          //$(date).append('<option value="">' + row['created'] + '</option>');
          timeList.push(row['get_time'].split(" ")[0]);
        }
        const set = new Set(timeList);
        let timeArray = Array.from(set);
        for(let j = 0; j < timeArray.length; j++){
          $(date).append(`<option value=${timeArray[j]}>${timeArray[j]}</option>`);
        }
      }
    });
  });