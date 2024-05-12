-- POST DATA
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());
INSERT INTO POST(title, writer, content, created_at, updated_at) VALUES ('제목', '민수', '내용', NOW(), NOW());

-- COMMENT DATA
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (1, '철수', '댓글', 'N', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (1, '철수', '댓글', 'N', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (1, '철수', '댓글', 'Y', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (1, '철수', '댓글', 'Y', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (1, '철수', '댓글', 'Y', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (2, '철수', '댓글', 'N', NOW(), NOW());
INSERT INTO COMMENT(post_id, writer, content, delete, created_at, updated_at) VALUES (2, '철수', '댓글', 'N', NOW(), NOW());