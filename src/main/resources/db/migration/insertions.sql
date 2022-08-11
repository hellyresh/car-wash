INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (1, 'vitaliy', null, '$2a$06$euJwpZb56p3RYOfdG7v32ORT.wAmjDyX/LO.v0gPKHo4CT0Vr8Nwy', 'USER', 'uservitaliy');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (6, 'vasya', null, '$2a$06$4oUDR.yVoh/nHyX3..tUEOgflGbaf0lpmhds2lGC1ezrTi7BqS1tq', 'OPERATOR', 'opervasya');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (7, 'sasha', null, '$2a$06$NTn3O6Q0Inq9g8wjmWBe2O59p2HWKFyxYXFBeOskL0inHppvMQhDy', 'OPERATOR', 'opersasha');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (9, 'lena', null, '$2a$06$6Vr5nv8Am8D.y3KIkI0nteeL4ImXQJO9zB67SvKyJqsTFZ8W40IzS', 'ADMIN', 'admin');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (5, 'katya', 'vasenina', '$2a$06$..lbzGK58UIupWx1ZzBGkeH6m.x6xzOaqBXIaVVsI9ouQS05xoEMi', 'OPERATOR',
        'operkatya');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (2, 'masha', 'sokolova', '$2a$06$uvlntlrjG6dXaNf23C1g9u59aM3W.wgYb0A8c.uKb1Q9HF.i94fem', 'USER', 'usermasha');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (10, 'igor', 'krutoy', '$2a$06$scPWQguAbyWFMjQvyEy5ZuRU3CmQYKM1GszgohTi0qzidA3OtaXoS', 'OPERATOR', 'operigor');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (8, 'rob', 'pattinson', '$2a$06$L5f798zSt80nMsywjEHZCuGqgFECWwE5VAazQU3u0v/EYHFQ5i5Dq', 'USER', 'userrobert');
INSERT INTO public.users (id, first_name, last_name, password, role, username)
VALUES (3, 'alex', 'myagkov', '$2a$06$2hFg/xqhZoq87wfdTr52G.Vy8lGooSrk.gt1X1rl93Kn8w22Jv6nW', 'USER', 'useralex');

INSERT INTO public.boxes (id, close_time, open_time, time_coefficient)
VALUES (1, '18:00:00', '12:00:00', 0.8);
INSERT INTO public.boxes (id, close_time, open_time, time_coefficient)
VALUES (4, '20:00:00', '10:40:00', 0.7);
INSERT INTO public.boxes (id, close_time, open_time, time_coefficient)
VALUES (2, '20:00:00', '08:00:00', 1);
INSERT INTO public.boxes (id, close_time, open_time, time_coefficient)
VALUES (3, '20:00:00', '08:00:00', 1.5);
INSERT INTO public.boxes (id, close_time, open_time, time_coefficient)
VALUES (5, '16:00:00', '06:30:00', 1.1);

INSERT INTO public.operators (id, max_discount, min_discount, box_id, user_id)
VALUES (1, 30, 10, 1, 6);
INSERT INTO public.operators (id, max_discount, min_discount, box_id, user_id)
VALUES (2, 15, 5, 2, 5);
INSERT INTO public.operators (id, max_discount, min_discount, box_id, user_id)
VALUES (3, 10, 10, 4, 7);
INSERT INTO public.operators (id, max_discount, min_discount, box_id, user_id)
VALUES (6, 23, 8, 5, 10);

INSERT INTO public.offers (id, duration, name, price)
VALUES (2, 30, 'simple washing', 500.00);
INSERT INTO public.offers (id, duration, name, price)
VALUES (3, 60, 'outside and inside washing', 1100.00);
INSERT INTO public.offers (id, duration, name, price)
VALUES (4, 90, 'dry cleaning + simple washing', 1600.00);

INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (1, '2022-08-11 14:40:00.000000', null, 60, 1100.00, 'SUBMITTED', 2, 3, 1);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (2, '2022-08-12 08:00:00.000000', null, 30, 500.00, 'SUBMITTED', 2, 2, 1);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (3, '2022-08-11 14:30:00.000000', null, 72, 1600.00, 'SUBMITTED', 1, 4, 2);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (4, '2022-08-12 08:30:00.000000', null, 30, 500.00, 'SUBMITTED', 2, 2, 2);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (5, '2022-08-12 09:00:00.000000', null, 30, 500.00, 'SUBMITTED', 2, 2, 3);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (6, '2022-08-11 14:30:00.000000', null, 63, 1600.00, 'SUBMITTED', 4, 4, 3);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (7, '2022-08-11 15:40:00.000000', null, 60, 1100.00, 'SUBMITTED', 2, 3, 8);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (8, '2022-08-12 15:40:00.000000', null, 90, 1600.00, 'SUBMITTED', 2, 4, 8);
INSERT INTO public.orders (id, date_time, discount, duration, price, status, box_id, offer_id, user_id)
VALUES (9, '2022-08-11 06:30:00.000000', 13, 66, 1100.00, 'FINISHED', 5, 3, 8);

INSERT INTO public.bills (id, date, offer_name, order_id, price, user_id)
VALUES (2, '2022-08-11 06:13:15.644933', 'outside and inside washing', 9, 957.00, 8);
