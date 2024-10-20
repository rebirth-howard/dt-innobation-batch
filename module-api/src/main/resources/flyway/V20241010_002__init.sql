-- 테이블 코멘트 추가
ALTER TABLE PRIVILEGE COMMENT = '권한';
ALTER TABLE PRIVILEGE_GROUP COMMENT = '권한그룹';
ALTER TABLE ROLE COMMENT = '역할';
ALTER TABLE ROLE_GROUP COMMENT = '역할그룹';
ALTER TABLE ROLE_PRIVILEGE_GROUP_MAPPING COMMENT = '역할-권한그룹 맵핑';
ALTER TABLE USER COMMENT = '사용자';


-- PRIVILEGE 테이블 컬럼 순서 조정
ALTER TABLE PRIVILEGE MODIFY COLUMN `NAME` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '권한 명' AFTER `ID`;
-- PRIVILEGE_GROUP 테이블 컬럼 순서 조정
ALTER TABLE PRIVILEGE_GROUP MODIFY COLUMN `PRIVILEGE_IDS` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '권한 IDS' AFTER `ID`;
-- ROLE 테이블 컬럼 순서 조정
ALTER TABLE ROLE MODIFY COLUMN `NAME` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '역할 명' AFTER `ID`;
ALTER TABLE ROLE MODIFY COLUMN `TYPE` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '역할 유형' AFTER `NAME`;
-- ROLE_GROUP 테이블 컬럼 순서 조정
ALTER TABLE ROLE_GROUP MODIFY COLUMN `ROLE_IDS` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '역할 IDS' AFTER `ID`;
ALTER TABLE ROLE_GROUP MODIFY COLUMN `TYPE` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '역할 그룹 유형' AFTER `ROLE_IDS`;
-- ROLE_PRIVILEGE_GROUP_MAPPING 테이블 컬럼 순서 조정
ALTER TABLE ROLE_PRIVILEGE_GROUP_MAPPING MODIFY COLUMN `PRIVILEGE_GROUP_ID` BIGINT NOT NULL COMMENT '권한 그룹 ID' AFTER `ID`;
ALTER TABLE ROLE_PRIVILEGE_GROUP_MAPPING MODIFY COLUMN `ROLE_ID` BIGINT NOT NULL COMMENT '역할 ID' AFTER `PRIVILEGE_GROUP_ID`;
-- USER 테이블 컬럼 순서 조정
ALTER TABLE USER MODIFY COLUMN `LOGIN_ID` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '로그인 아이디' AFTER `ID`;
ALTER TABLE USER MODIFY COLUMN `USER_NAME` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '로그인 아이디' AFTER `LOGIN_ID`;
ALTER TABLE USER MODIFY COLUMN `PASSWORD` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '비밀번호' AFTER `USER_NAME`;
ALTER TABLE USER MODIFY COLUMN `ROLE_GROUP_ID` BIGINT NOT NULL COMMENT '역할 그룹 ID' AFTER `PASSWORD`;
ALTER TABLE USER MODIFY COLUMN `STATUS` ENUM('UNAPPROVED','UNDER_REVIEW','REVIEW_REJECTED','APPROVED','SUSPENDED','PENDING_WITHDRAWAL','WITHDRAWN') COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '상태' AFTER `ROLE_GROUP_ID`;

-- UUID 값을 변수에 저장
SET @createdByUUID = UUID();
SET @updatedByUUID = @createdByUUID;


-- PRIVILEGE 테이블 데이터 생성
INSERT INTO PRIVILEGE (NAME, CREATED_BY, UPDATED_BY)
VALUES
('회사소개', @createdByUUID, @updatedByUUID),
('회사소개 - 인사말', @createdByUUID, @updatedByUUID),
('회사소개 - 기업현황', @createdByUUID, @updatedByUUID),
('회사소개 - 조직도', @createdByUUID, @updatedByUUID),
('회사소개 - 찾아오시는 길', @createdByUUID, @updatedByUUID),
('고객센터', @createdByUUID, @updatedByUUID),
('고객센터 - 공지사항', @createdByUUID, @updatedByUUID),
('고객센터 - 자주묻는질문', @createdByUUID, @updatedByUUID),
('마이페이지', @createdByUUID, @updatedByUUID),
('마이페이지 - 차량경매', @createdByUUID, @updatedByUUID),
('마이페이지 - 등록차량', @createdByUUID, @updatedByUUID),
('마이페이지 - 관심차량', @createdByUUID, @updatedByUUID),
('마이페이지 - 입찰차량', @createdByUUID, @updatedByUUID),
('마이페이지 - 낙찰차량', @createdByUUID, @updatedByUUID),
('부품매매 - 관심부품', @createdByUUID, @updatedByUUID),
('부품매매 - 구매부품', @createdByUUID, @updatedByUUID),
('부품매매 - 취소부품', @createdByUUID, @updatedByUUID),
('부품매매 - 반품부품', @createdByUUID, @updatedByUUID),
('부품매매 - 1:1문의내역', @createdByUUID, @updatedByUUID),
('개인 정보 수정', @createdByUUID, @updatedByUUID),
('차량등록', @createdByUUID, @updatedByUUID),
('보험경매', @createdByUUID, @updatedByUUID),
('중고품 매매', @createdByUUID, @updatedByUUID),
('사이트관리', @createdByUUID, @updatedByUUID),
('사이트관리 - 공지사항', @createdByUUID, @updatedByUUID),
('회원관리 - 일반', @createdByUUID, @updatedByUUID),
('회원관리 - 보험사', @createdByUUID, @updatedByUUID),
('회원관리 - 부품 판매처', @createdByUUID, @updatedByUUID),
('경매관리', @createdByUUID, @updatedByUUID),
('경매관리 - 경매차량', @createdByUUID, @updatedByUUID),
('경매관리 - 경매차량 등록', @createdByUUID, @updatedByUUID),
('경매관리 - 경매차 출품처', @createdByUUID, @updatedByUUID),
('부품관리', @createdByUUID, @updatedByUUID),
('부핌관리 - 부품판매', @createdByUUID, @updatedByUUID),
('부핌관리 - 부품주문', @createdByUUID, @updatedByUUID),
('부핌관리 - 부품재고', @createdByUUID, @updatedByUUID),
('부핌관리 - 부품품목', @createdByUUID, @updatedByUUID),
('관리자기능', @createdByUUID, @updatedByUUID),
('관리자기능 - 회원권한', @createdByUUID, @updatedByUUID);

-- PRIVILEGE_GROUP 테이블 데이터 생성
INSERT INTO PRIVILEGE_GROUP (PRIVILEGE_IDS, CREATED_BY, UPDATED_BY)
VALUES
('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38', @createdByUUID, @updatedByUUID), -- 슈퍼관리자
('', @createdByUUID, @updatedByUUID), -- 임시
('1,2,3,4,5,6,7,8,9,12,13,14,15,16,17,18,19,20,22,23', @createdByUUID, @updatedByUUID), -- 일반
('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', @createdByUUID, @updatedByUUID), -- 보험사
('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', @createdByUUID, @updatedByUUID), -- 보험사-파트너
('1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,22,23,33,34,35,36,37', @createdByUUID, @updatedByUUID); -- 판매처

-- ROLE 테이블 데이터 생성
INSERT INTO ROLE (TYPE, NAME, CREATED_BY, UPDATED_BY)
VALUES
('ROLE_SUPER_ADMIN', '슈퍼관리자', @createdByUUID, @updatedByUUID),
('ROLE_TEMPORARY', '임시', @createdByUUID, @updatedByUUID),
('ROLE_USER', '일반', @createdByUUID, @updatedByUUID),
('ROLE_INSURER', '보험사', @createdByUUID, @updatedByUUID),
('ROLE_INSURER_PARTNER', '보험사-파트너', @createdByUUID, @updatedByUUID),
('ROLE_VENDOR', '판매처', @createdByUUID, @updatedByUUID);

-- ROLE_GROUP 테이블에 데이터 삽입
INSERT INTO ROLE_GROUP (TYPE, ROLE_IDS, CREATED_BY, UPDATED_BY)
VALUES
('슈퍼관리자 그룹', '1,2,3', @createdByUUID, @updatedByUUID), -- 임시, 일반, 슈퍼관리자
('임시 그룹', '2', @createdByUUID, @updatedByUUID), -- 임시
('일반 그룹', '2,3', @createdByUUID, @updatedByUUID), -- 임시, 일반
('보험사 그룹', '2,3,4', @createdByUUID, @updatedByUUID), -- 임시, 일반, 보험사
('보험사-파트너 그룹', '2,3,5', @createdByUUID, @updatedByUUID), -- 임시, 일반, 보험사-파트너
('판매처 그룹', '2,3,6', @createdByUUID, @updatedByUUID); -- 임시, 일반, 판매처

-- ROLE과 PRIVILEGE_GROUP 간 매핑 데이터 생성
INSERT INTO ROLE_PRIVILEGE_GROUP_MAPPING (ROLE_ID, PRIVILEGE_GROUP_ID, CREATED_BY, UPDATED_BY)
VALUES
-- 슈퍼관리자 -> 모든 PRIVILEGE_GROUP에 대한 권한 부여
(1, 1, @createdByUUID, @updatedByUUID),  -- 슈퍼관리자
(2, 2, @createdByUUID, @updatedByUUID),  -- 임시
(3, 3, @createdByUUID, @updatedByUUID),  -- 일반
(4, 4, @createdByUUID, @updatedByUUID),  -- 보험사
(5, 5, @createdByUUID, @updatedByUUID),  -- 보험사-파트너
(6, 6, @createdByUUID, @updatedByUUID);  -- 판매처

-- USER 테이블에 데이터 삽입
INSERT INTO `USER` (ID, LOGIN_ID, USER_NAME, PASSWORD, ROLE_GROUP_ID, STATUS, CREATED_BY, UPDATED_BY)
VALUES
(@createdByUUID, 'user_admin', '슈퍼관리자', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 1, 'APPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_normal', '일반', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 3, 'APPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_ins', '보험사', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 4, 'APPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_ins_partner', '보험사(파트너)', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 5, 'APPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_sell', '판매처', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 6, 'APPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_temp', '임시유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 2, 'UNAPPROVED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_audit', '심사중유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 2, 'UNDER_REVIEW', @createdByUUID, @updatedByUUID),
(UUID(), 'user_turn', '반려유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 2, 'REVIEW_REJECTED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_stop', '활동정지유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 3, 'SUSPENDED', @createdByUUID, @updatedByUUID),
(UUID(), 'user_withdraw_p', '탈퇴예정유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 3, 'PENDING_WITHDRAWAL', @createdByUUID, @updatedByUUID),
(UUID(), 'user_withdraw_c', '탈퇴확정유저', '{bcrypt}$2a$10$Q6SI.EzWwez/NYbO5a4UpOmajhnzYpRmpps3Xwfdur9xqzD4oZ7Ra', 3, 'WITHDRAWN', @createdByUUID, @updatedByUUID);

