   
    /*<![CDATA[*/


   
 
      $(document).ready(function() {

        // $('#contents').on('click', '.modifyPost', function() {
        //   alert('call');
        //   let post_id = $(this).data('id')
        //   console.log('post_id : ', post_id);
        // });

        // $('.deletePost').on('click', function() {


        // });



        //게시글 




      //모달 띄우기 
    	 $('#contents').on('click', 'a', function(e) {
              e.preventDefault();
              $('#modifyForm').hide();
              $('#addForm').show();
              post_id = $(this).data('id');            
              
              //게시글 상세 조회
               $.ajax({
                 url: "/posts/" + post_id,
                 type: 'GET',
                 contentType: 'application/json;charset=utf-8',
                 dataType: 'json',
                 success: function (data) {  
                  getPostTitle(data);
                  getPostPhoto(data);
                  getPostAccount(data);    
                },
                fail: function(ex){
                  console.log("error: ", ex);
                }

              });

              //좋아요 조회
              $.ajax({
                url: "/posts/" + post_id + "/likes",
                type: 'GET',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {  
                 getPostLike(data);
                   
               },
               fail: function(ex){
                 console.log("error: ", ex);
               }

             });

              //댓글 조회
              $.ajax({
                url: "/posts/" + post_id + "/replies",
                type: 'GET',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {      
                  getCommentList(data);
                },
                fail: function (ex) {
                  console.log("error : ", ex);
                }
              });

              $('#modalScrollable').modal('show');

         })
       
        //댓글 등록
        $('#addComment').on("click", function () {

          $.ajax({
            url: "/posts/" + post_id + "/replies",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
              replyContent: $('#replyContent').val()
            }),

            success: function (data) {
              $('#replyContent').val("");
              getCommentList(data);
            },
            fail: function(ex) {
              console.log("error: ", ex);
            }
          });

        });
        //댓글 등록 엔터키 이벤트
        $('#replyContent').keydown(function(keynum){
          if(keynum.keyCode == 13){ //엔터키 이벤트 발생
            
              $.ajax({
                url: "/posts/" + post_id + "/replies",
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: JSON.stringify({
                  replyContent: $('#replyContent').val()
                }),

                success: function (data) {
                  $('#replyContent').val("");
                  getCommentList(data);
                },
                fail: function (ex) {
                  console.log("error: ", ex);
                }
            });
          }
        }); 
        

        //댓글 삭제
        $('#commentList').on("click", ".subComment", function () {

          let reply_id = $(this).closest("div").data("id");

          //console.log("postid: ", post_id);
          //console.log("replyid: ", reply_id);

          $.ajax({
            url: "/posts/" + post_id + "/replies/" + reply_id,
            type: 'DELETE',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',

            success: function (data) {
              getCommentList(data);
            },
            
            fail: function(ex) {
              console.log("error: ", ex);
            }

          });

        });

        
        //댓글 수정 폼 생성
        $('#commentList').on("click", ".modComment", function(){

          modifyForm_reply_id = $(this).closest("div").data("id");
          //console.log("modifyReply: ", modifyForm_reply_id );

          $('#addForm').hide();
          $('#modifyForm').show();


        });

        //댓글 수정
        $('#modifyForm').on("click",'#ok', function(){
          let reply_id = modifyForm_reply_id;
          
          $.ajax({
            url: "posts/" + post_id + "/replies/" + reply_id,
            type: 'PUT',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
              replyContent: $('#modContent').val()
            }),

            success: function(data){
              $('#modContent').val("");
              $('#modifyForm').hide();
              $('#addForm').show();
              getCommentList(data);
            },
            error: function(ex){
              console.log(ex);
            }
            
          });

        });
        //댓글 수정 엔터키 이벤트
        $('#modContent').keydown(function(keynum){
          let reply_id = modifyForm_reply_id;

          if(keynum.keyCode == 13){ //엔터키 이벤트 발생
            
            $.ajax({
              url: "posts/" + post_id + "/replies/" + reply_id,
              type: 'PUT',
              contentType: 'application/json;charset=utf-8',
              dataType: 'json',
              data: JSON.stringify({
                replyContent: $('#modContent').val()
              }),

              success: function (data) {
                $('#modContent').val("");
                $('#modifyForm').hide();
                $('#addForm').show();
                getCommentList(data);
              },
              error: function (ex) {
                console.log(ex);
              }
            });
          }
        }); 

            //게시글 제목 조회
              function getPostTitle(data) {
                  let post = data.post;
            
                  let htmlStr = "";

                  htmlStr += "<h5 class='modal-title' id='modalScrollableTitle'>"+ post.post_title +"</h5>";
                  htmlStr += "<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>";	           

                  $('#postTitle').html("");
                  $('#postTitle').append(htmlStr);

                }

            //게시글 이미지 상세 조회
              function getPostPhoto(data) {
              let post = data.post;
              
              let htmlStr = "";


              for (let i = 0; i < post.uploads.length; i++) {
                htmlStr += "<div class='carousel-item active'>";
                htmlStr += "<img class='d-block w-100' src='/upload/" + post.uploads[i].upload_filepath + "/" + post.uploads[i].upload_fileuuid + "_" + post.uploads[i].upload_filename + "\' alt='Third slide'  />";
                htmlStr += "</div>";

              }

              $('#postPhoto').html("");
              $('#postPhoto').append(htmlStr);
              $('.carousel-item:nth-child(2),.carousel-item:nth-child(3)').removeClass('active');


            }    
           
            //게시글 작성자 조회
              function getPostAccount(data) {
              let post = data.post;

              let htmlStr = "";



              htmlStr += "<div class='profile-img' style='background: url(/img/avatars/1.jpg) no-repeat center; background-size: cover;'></div>";
              htmlStr += "<div class='user_container'>";
              htmlStr += "<div class='user_name'>";
              htmlStr += "<div class='nick_name'>" + post.member_nick + "</div>";
              htmlStr += "</div>";
              htmlStr += "<div class='country'>" + post.post_content + "</div>";
              htmlStr += "</div>";


              $('#postAccount').html("");
              $('#postAccount').append(htmlStr);

            }


            //게시글 좋아요
              function getPostLike(data) {
              
              let post = data.post;
              

              let htmlStr1 = "<a href='#'><i class='bx bx-heart'></i></a>";
              let htmlStr = "좋아요<span class='count'>"+ post.likeCount + "</span>개";
            
              


              $('#postLikeCount, #postLikeBtn').html("");
              $('#postLikeBtn').append(htmlStr1);
              $('#postLikeCount').append(htmlStr);

            }


        //댓글 리스트 조회
        function getCommentList(data) {

          let replyList = data.replyList;
          let htmlStr = "";

          for (let i = 0; i < replyList.length; i++) {
            htmlStr += "<div class='admin_container' id=" + replyList[i].replyId + ">";
            htmlStr += "<div class= 'profile-img' style='background: url(/img/avatars/1.jpg) no-repeat center; background-size: cover;'></div>";
            htmlStr += "<div class='comment'>";
            htmlStr += "<span class='user_id'>" + replyList[i].memberNick + "</span>" + "<div>" + replyList[i].replyContent + "</div>";
            htmlStr += "<div class='time'>" + replyList[i].replyRegdate + "</div>";
            htmlStr += "<div class='time reply-btn' data-id=" + replyList[i].replyId + ">";
            if (replyList[i].memberNick == memberNick) {
              htmlStr += "<a class='modComment'>수정</a>";
              htmlStr += "<a class='subComment'>삭제</a></div>";
            }
            htmlStr += "<div class='reply_edit'><a href='#n'></a></div>";
            htmlStr += "<div class='reply_delete'><a href='#n'></a></div>";
            htmlStr += "<div class='reply_heart_btn'>";
            htmlStr += "<a href='#'><i class='bx bx-heart'></i><span>8</span></a>";
            htmlStr += "</div></div></div>";
          }

          $('#commentList').html("");
          $('#commentList').append(htmlStr);

        }

        
      });




    
      /*]]>*/