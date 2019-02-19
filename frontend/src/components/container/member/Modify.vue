<template>
  <div class="outer_register">
    <div class="register_area">
      <div class="inner_register">
        <div class="hgroup">
            <h3 class="title">회원정보 수정</h3>
            <p class="descript">정보를 수정할 수 있습니다.</p>
        </div><!-- // hgroup -->

        <form name="frm" action="#" method="post">
          <fieldset>
            <legend class="invisible">회원정보 수정</legend>

            <div class="inner_fieldset">
              <div class="inputfield_outer">
                <span>아이디</span>
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="password">패스워드</label>
                <input type="password" name="password" id="password" class="inputfield_global" value="" placeholder="패스워드를 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="passwordCheck">패스워드 재확인</label>
                <input type="password" name="passwordCheck" id="passwordCheck" class="inputfield_global" value="" placeholder="패스워드 확인을 위해 한 번 더 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="quiz">퀴즈</label>
                <input type="text" name="quiz" id="quiz" class="inputfield_global" value="" placeholder="직접 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <span>이름</span>
              </div><!-- // inputfield_outer -->
              <hr />
              <div class="inputfield_outer">
                <span>생년월일</span>
                <input type="number" name="birthday1" class="inputfield_global" value="" placeholder="년도를 입력해주세요." />

                <div class="design_select">
                  <strong class="invisible">월 선택</strong>

                  <span>월</span>

                  <select name="birthday2">
                    <option value="" selected>-- 선택 --</option>
                    <option value="01">1월</option>
                    <option value="02">2월</option>
                    <option value="03">3월</option>
                    <option value="04">4월</option>
                    <option value="05">5월</option>
                    <option value="06">6월</option>
                    <option value="07">7월</option>
                    <option value="08">8월</option>
                    <option value="09">9월</option>
                    <option value="10">10월</option>
                    <option value="11">11월</option>
                    <option value="12">12월</option>
                  </select>
                </div><!-- // design_select -->

                <input type="number" name="birthday3" id="birthday" class="inputfield_global" value="" placeholder="일을 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <span>전화번호</span>

                <div class="design_select">
                  <strong class="invisible">지역번호 선택</strong>

                  <span>-- 선택 --</span>

                  <select name="tel1">
                    <option value="" selected>-- 선택 --</option>
                    <option value="02">(서울) 02</option>
                    <option value="051">(부산) 051</option>
                    <option value="053">(대구) 053</option>
                    <option value="032">(인천) 032</option>
                    <option value="062">(광주) 062</option>
                    <option value="042">(대전) 042</option>
                    <option value="052">(울산) 052</option>
                    <option value="044">(세종) 044</option>
                    <option value="031">(경기) 031</option>
                    <option value="033">(강원) 033</option>
                    <option value="043">(충북) 043</option>
                    <option value="041">(충남) 041</option>
                    <option value="063">(전북) 063</option>
                    <option value="061">(전남) 061</option>
                    <option value="054">(경북) 054</option>
                    <option value="055">(경남) 055</option>
                    <option value="064">(제주) 064</option>
                  </select>
                </div><!-- // design_select -->

                <input type="tel" name="tel2" class="inputfield_global" value="" placeholder="전화번호 앞자리를 입력해주세요." />
                <input type="tel" name="tel3" class="inputfield_global" value="" placeholder="전화번호 뒷자리를 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <span>휴대폰 번호</span>

                <div class="design_select">
                  <strong class="invisible">통신사 선택</strong>

                  <span>-- 선택 --</span>

                  <select name="service">
                    <option value="" selected>-- 선택 --</option>
                    <option value="skt">SKT</option>
                    <option value="kt">KT</option>
                    <option value="lg">LG</option>
                  </select>
                </div><!-- // design_select -->

                <input type="tel" name="phone1" class="inputfield_global" value="" placeholder="휴대폰 번호 첫 번째 자리를 입력해주세요." />
                <input type="tel" name="phone2" class="inputfield_global" value="" placeholder="휴대폰 번호 두 번째 자리를 입력해주세요." />
                <input type="tel" name="phone3" class="inputfield_global" value="" placeholder="휴대폰 번호 세 번째 자리를 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <span>주소</span>
                <input type="number" name="postcode" id="postcode" class="inputfield_global" value="" placeholder="우편번호를 입력해주세요." />
                <input type="text" name="address" id="address" class="inputfield_global" value="" placeholder="나머지 주소를 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="email">이메일 주소</label>
                <input type="text" name="email1" id="email" class="inputfield_global" value="" placeholder="이메일 주소 앞자리를 입력해주세요." />@<input type="text" name="email2" class="inputfield_global" value="" placeholder="직접 입력해주세요." />
              </div><!-- // inputfield_outer -->

              <button type="submit" class="button_global button_join">수정</button>
              <a href="#">메인 페이지로 이동</a>
            </div><!-- // inner_fieldset -->
          </fieldset>
        </form>
      </div><!-- // register_area -->
    </div><!-- // inner_register -->
  </div><!-- // outer_register -->
</template>

<script>
// import Constant from '../../../Constant.js'

export default {
  name: 'modify',
  mounted () {
    // this.$store.commit(Constant.GNB, {gnb: true})
  }
}
</script>
