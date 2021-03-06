let index={
    init:function(){
        $("#btn-save").on("click", ()=>{// function(){}, ()=>{}사용이유는 this를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
    },

    save:function(){
        //alert('user의 save함수 호출됨');
        let data={                  //자바스크립트 오브젝트
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

       // console.log(data); -> 자바스크립트 오브젝트
       // console.log(JSON.stringify(data)); -> json 문자열

       //ajax호출시 default가 비동기 호출
       //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브텍트로 변환해준다.
        $.ajax({
        //회원가입 수행 요청
            type:"POST",   //요청방식은?
            url:"/auth/joinProc", //어느 주소로 호출함?
            data:JSON.stringify(data), //자바쪽으로 json을 던짐, httpd body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(resp){
            if(resp.status === 500){
                alert("회원가입 실패");
            }else{
                alert("회원가입이 완료되었습니다.");
                location.href="/";
            }
        }).fail(function(error){
        //실패면
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

    },

    update:function(){
        //alert('user의 save함수 호출됨');
        let data={ //자바스크립트 오브젝트
            id:$("#id").val(),
            username: $("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

       // console.log(data); -> 자바스크립트 오브젝트
       // console.log(JSON.stringify(data)); -> json 문자열

       //ajax호출시 default가 비동기 호출
       //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브텍트로 변환해준다.
        $.ajax({
        //회원가입 수행 요청
            type:"PUT",   //요청방식은?
            url:"/user", //어느 주소로 호출함?
            data:JSON.stringify(data), //자바쪽으로 json을 던짐, httpd body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(resp){
        //정상이면
            alert("회원수정이 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
        //실패면
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청


    }

}

index.init();