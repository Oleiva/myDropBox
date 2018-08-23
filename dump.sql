--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.13
-- Dumped by pg_dump version 9.5.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: company; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.company (
    id bigint NOT NULL,
    client_secret character varying(255),
    name character varying(255) NOT NULL,
    company_role character varying(11),
    CONSTRAINT ck_company_company_role CHECK (((company_role)::text = ANY ((ARRAY['Engineering'::character varying, 'DevOps'::character varying, 'Manegement'::character varying, 'Clevel'::character varying, 'Other'::character varying])::text[])))
);


ALTER TABLE public.company OWNER TO monitor;

--
-- Name: company_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_id_seq OWNER TO monitor;

--
-- Name: company_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.company_id_seq OWNED BY public.company.id;


--
-- Name: company_invite; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.company_invite (
    id bigint NOT NULL,
    company_id bigint,
    company_invite character varying(255)
);


ALTER TABLE public.company_invite OWNER TO monitor;

--
-- Name: company_invite_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.company_invite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_invite_id_seq OWNER TO monitor;

--
-- Name: company_invite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.company_invite_id_seq OWNED BY public.company_invite.id;


--
-- Name: company_trial; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.company_trial (
    id bigint NOT NULL,
    company_id bigint,
    is_trial boolean,
    start_trial timestamp without time zone,
    host_id bigint
);


ALTER TABLE public.company_trial OWNER TO monitor;

--
-- Name: company_trial_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.company_trial_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_trial_id_seq OWNER TO monitor;

--
-- Name: company_trial_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.company_trial_id_seq OWNED BY public.company_trial.id;


--
-- Name: host; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.host (
    id bigint NOT NULL,
    server_name character varying(255),
    server_os character varying(255),
    server_ip character varying(255),
    host_id character varying(255),
    host_status integer,
    last_access_problem timestamp without time zone,
    host_company_id bigint,
    CONSTRAINT ck_host_host_status CHECK ((host_status = ANY (ARRAY[0, 1, 2, 3, 4, 5, 6])))
);


ALTER TABLE public.host OWNER TO monitor;

--
-- Name: host_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.host_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.host_id_seq OWNER TO monitor;

--
-- Name: host_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.host_id_seq OWNED BY public.host.id;


--
-- Name: issue; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.issue (
    id integer NOT NULL,
    issue_id character varying(255) NOT NULL,
    company_id bigint,
    server_name character varying(255) NOT NULL,
    start_time timestamp without time zone,
    finish_time timestamp without time zone,
    alert_text character varying(255),
    status character varying(10) NOT NULL,
    is_checked boolean,
    CONSTRAINT ck_issue_status CHECK (((status)::text = ANY ((ARRAY['InProgress'::character varying, 'Resolved'::character varying, 'Instant'::character varying])::text[])))
);


ALTER TABLE public.issue OWNER TO monitor;

--
-- Name: issue_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.issue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.issue_id_seq OWNER TO monitor;

--
-- Name: issue_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.issue_id_seq OWNED BY public.issue.id;


--
-- Name: magento_host; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.magento_host (
    id bigint NOT NULL,
    server_name character varying(255),
    server_url character varying(255),
    server_os character varying(255),
    server_ip character varying(255),
    host_id character varying(255),
    host_company_id bigint
);


ALTER TABLE public.magento_host OWNER TO monitor;

--
-- Name: magento_host_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.magento_host_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.magento_host_id_seq OWNER TO monitor;

--
-- Name: magento_host_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.magento_host_id_seq OWNED BY public.magento_host.id;


--
-- Name: password_token; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.password_token (
    id integer NOT NULL,
    token character varying(255),
    user_id bigint,
    email character varying(255),
    date_of_creation timestamp without time zone
);


ALTER TABLE public.password_token OWNER TO monitor;

--
-- Name: password_token_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.password_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.password_token_id_seq OWNER TO monitor;

--
-- Name: password_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.password_token_id_seq OWNED BY public.password_token.id;


--
-- Name: play_evolutions; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.play_evolutions (
    id integer NOT NULL,
    hash character varying(255) NOT NULL,
    applied_at timestamp without time zone NOT NULL,
    apply_script text,
    revert_script text,
    state character varying(255),
    last_problem text
);


ALTER TABLE public.play_evolutions OWNER TO monitor;

--
-- Name: promo_code; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.promo_code (
    id bigint NOT NULL,
    company_id bigint,
    promocode character varying(255),
    is_used boolean,
    time_of_creation timestamp without time zone,
    time_expiration timestamp without time zone,
    time_when_used timestamp without time zone
);


ALTER TABLE public.promo_code OWNER TO monitor;

--
-- Name: promo_code_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.promo_code_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.promo_code_id_seq OWNER TO monitor;

--
-- Name: promo_code_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.promo_code_id_seq OWNED BY public.promo_code.id;


--
-- Name: rateplans; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.rateplans (
    id integer NOT NULL,
    nameofplan character varying(255),
    hosttype integer,
    price character varying(255),
    description character varying(255),
    CONSTRAINT ck_rateplans_hosttype CHECK ((hosttype = ANY (ARRAY[0, 1, 2])))
);


ALTER TABLE public.rateplans OWNER TO monitor;

--
-- Name: rateplans_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.rateplans_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rateplans_id_seq OWNER TO monitor;

--
-- Name: rateplans_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.rateplans_id_seq OWNED BY public.rateplans.id;


--
-- Name: subscriptions; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.subscriptions (
    id bigint NOT NULL,
    company_id bigint,
    host_id bigint,
    tr_monitor_id character varying(255),
    host_type integer,
    quota_id character varying(255),
    status integer,
    next_billing_date timestamp without time zone,
    last_payment_date timestamp without time zone,
    final_payment_date timestamp without time zone,
    monitor_time_of_creation timestamp without time zone,
    is_trial boolean,
    CONSTRAINT ck_subscriptions_host_type CHECK ((host_type = ANY (ARRAY[0, 1, 2]))),
    CONSTRAINT ck_subscriptions_status CHECK ((status = ANY (ARRAY[0, 1, 2])))
);


ALTER TABLE public.subscriptions OWNER TO monitor;

--
-- Name: subscriptions_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.subscriptions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subscriptions_id_seq OWNER TO monitor;

--
-- Name: subscriptions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.subscriptions_id_seq OWNED BY public.subscriptions.id;


--
-- Name: transactions; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.transactions (
    id bigint NOT NULL,
    tr_monitor_id character varying(255),
    company_id bigint,
    host_id character varying(255),
    host_type integer,
    tr_id character varying(255),
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    start_date timestamp without time zone,
    next_billing_date timestamp without time zone,
    last_payment_date timestamp without time zone,
    final_payment_date timestamp without time zone,
    payer_info_first_name character varying(255),
    payer_last_name character varying(255),
    payer_id character varying(255),
    payer_email character varying(255),
    monitor_time_of_creation timestamp without time zone,
    transaction character varying(255),
    CONSTRAINT ck_transactions_host_type CHECK ((host_type = ANY (ARRAY[0, 1, 2])))
);


ALTER TABLE public.transactions OWNER TO monitor;

--
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO monitor;

--
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- Name: trigger; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.trigger (
    id bigint NOT NULL,
    host_name character varying(255),
    trigger_id character varying(255),
    expression character varying(255),
    description character varying(255),
    comments character varying(255),
    host_id bigint
);


ALTER TABLE public.trigger OWNER TO monitor;

--
-- Name: trigger_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.trigger_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trigger_id_seq OWNER TO monitor;

--
-- Name: trigger_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.trigger_id_seq OWNED BY public.trigger.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: monitor
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    company_id bigint,
    is_verified boolean,
    email_code character varying(255),
    date_of_creation timestamp without time zone,
    role_id integer,
    timezone character varying(255),
    receive_notifications boolean,
    weekly_report_notifications boolean,
    billing_notifications boolean,
    crashes_notifications boolean,
    trends_notifications boolean,
    CONSTRAINT ck_users_role_id CHECK ((role_id = ANY (ARRAY[0, 1, 2])))
);


ALTER TABLE public.users OWNER TO monitor;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: monitor
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO monitor;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: monitor
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company ALTER COLUMN id SET DEFAULT nextval('public.company_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_invite ALTER COLUMN id SET DEFAULT nextval('public.company_invite_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_trial ALTER COLUMN id SET DEFAULT nextval('public.company_trial_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.host ALTER COLUMN id SET DEFAULT nextval('public.host_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.issue ALTER COLUMN id SET DEFAULT nextval('public.issue_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.magento_host ALTER COLUMN id SET DEFAULT nextval('public.magento_host_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.password_token ALTER COLUMN id SET DEFAULT nextval('public.password_token_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.promo_code ALTER COLUMN id SET DEFAULT nextval('public.promo_code_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.rateplans ALTER COLUMN id SET DEFAULT nextval('public.rateplans_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.subscriptions ALTER COLUMN id SET DEFAULT nextval('public.subscriptions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.trigger ALTER COLUMN id SET DEFAULT nextval('public.trigger_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.company (id, client_secret, name, company_role) FROM stdin;
1	658fc83e-bc4a-4abe-a3a8-9a0f9c89a14f	Iva	\N
15	658fc83e-bc4a-4abe-a3a8-9a0f9c89a14f	i7u	\N
2	87e465ba-64e0-4d96-b6b9-49ec640127b4	efefe	\N
3	8c2e11e4-5d9e-4177-9173-b29d005cf3c6	rfefrefe	\N
\.


--
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.company_id_seq', 3, true);


--
-- Data for Name: company_invite; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.company_invite (id, company_id, company_invite) FROM stdin;
1	1	a8402ff5-6ee2-4371-a940-62c43bef12b0
2	2	4aa3a597-e0dc-4d97-83ea-8563ba1099ea
3	3	8272d659-21cb-46f1-899e-b9130b2f4112
\.


--
-- Name: company_invite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.company_invite_id_seq', 3, true);


--
-- Data for Name: company_trial; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.company_trial (id, company_id, is_trial, start_trial, host_id) FROM stdin;
1	1	f	2018-08-22 18:11:42.729	\N
2	2	f	2018-08-23 16:22:33.335	\N
3	3	f	2018-08-23 16:26:43.425	\N
\.


--
-- Name: company_trial_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.company_trial_id_seq', 3, true);


--
-- Data for Name: host; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.host (id, server_name, server_os, server_ip, host_id, host_status, last_access_problem, host_company_id) FROM stdin;
2	\N	\N	\N	\N	2	\N	2
15	oleiva-Inspiron-3543	linux	192.168.1.55	10147	1	\N	15
\.


--
-- Name: host_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.host_id_seq', 3, true);


--
-- Data for Name: issue; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.issue (id, issue_id, company_id, server_name, start_time, finish_time, alert_text, status, is_checked) FROM stdin;
\.


--
-- Name: issue_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.issue_id_seq', 1, false);


--
-- Data for Name: magento_host; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.magento_host (id, server_name, server_url, server_os, server_ip, host_id, host_company_id) FROM stdin;
\.


--
-- Name: magento_host_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.magento_host_id_seq', 1, false);


--
-- Data for Name: password_token; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.password_token (id, token, user_id, email, date_of_creation) FROM stdin;
\.


--
-- Name: password_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.password_token_id_seq', 1, false);


--
-- Data for Name: play_evolutions; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.play_evolutions (id, hash, applied_at, apply_script, revert_script, state, last_problem) FROM stdin;
1	f82e8879c728819c9eeb5b9af05067d0795380f0	2018-08-22 00:00:00	create table company (\nid                        bigserial not null,\nclient_secret             varchar(255),\nname                      varchar(255) not null,\ncompany_role              varchar(11),\nconstraint ck_company_company_role check (company_role in ('Engineering','DevOps','Manegement','Clevel','Other')),\nconstraint uq_company_name unique (name),\nconstraint pk_company primary key (id))\n;\n\ncreate table company_invite (\nid                        bigserial not null,\ncompany_id                bigint,\ncompany_invite            varchar(255),\nconstraint uq_company_invite_company_id unique (company_id),\nconstraint pk_company_invite primary key (id))\n;\n\ncreate table company_trial (\nid                        bigserial not null,\ncompany_id                bigint,\nis_trial                  boolean,\nstart_trial               timestamp,\nhost_id                   bigint,\nconstraint uq_company_trial_company_id unique (company_id),\nconstraint pk_company_trial primary key (id))\n;\n\ncreate table host (\nid                        bigserial not null,\nserver_name               varchar(255),\nserver_os                 varchar(255),\nserver_ip                 varchar(255),\nhost_id                   varchar(255),\nhost_status               integer,\nlast_access_problem       timestamp,\nhost_company_id           bigint,\nconstraint ck_host_host_status check (host_status in (0,1,2,3,4,5,6)),\nconstraint pk_host primary key (id))\n;\n\ncreate table issue (\nid                        serial not null,\nissue_id                  varchar(255) not null,\ncompany_id                bigint,\nserver_name               varchar(255) not null,\nstart_time                timestamp,\nfinish_time               timestamp,\nalert_text                varchar(255),\nstatus                    varchar(10) not null,\nis_checked                boolean,\nconstraint ck_issue_status check (status in ('InProgress','Resolved','Instant')),\nconstraint pk_issue primary key (id))\n;\n\ncreate table magento_host (\nid                        bigserial not null,\nserver_name               varchar(255),\nserver_url                varchar(255),\nserver_os                 varchar(255),\nserver_ip                 varchar(255),\nhost_id                   varchar(255),\nhost_company_id           bigint,\nconstraint uq_magento_host_server_name unique (server_name),\nconstraint pk_magento_host primary key (id))\n;\n\ncreate table promo_code (\nid                        bigserial not null,\ncompany_id                bigint,\npromocode                 varchar(255),\nis_used                   boolean,\ntime_of_creation          timestamp,\ntime_expiration           timestamp,\ntime_when_used            timestamp,\nconstraint pk_promo_code primary key (id))\n;\n\ncreate table ratePlans (\nid                        serial not null,\nnameOfPlan                varchar(255),\nhostType                  integer,\nprice                     varchar(255),\ndescription               varchar(255),\nconstraint ck_ratePlans_hostType check (hostType in (0,1,2)),\nconstraint pk_ratePlans primary key (id))\n;\n\ncreate table password_token (\nid                        serial not null,\ntoken                     varchar(255),\nuser_id                   bigint,\nemail                     varchar(255),\ndate_of_creation          timestamp,\nconstraint pk_password_token primary key (id))\n;\n\ncreate table subscriptions (\nid                        bigserial not null,\ncompany_id                bigint,\nhost_id                   bigint,\ntr_monitor_id             varchar(255),\nhost_type                 integer,\nquota_id                  varchar(255),\nstatus                    integer,\nnext_billing_date         timestamp,\nlast_payment_date         timestamp,\nfinal_payment_date        timestamp,\nmonitor_time_of_creation  timestamp,\nis_Trial                  boolean,\nconstraint ck_subscriptions_host_type check (host_type in (0,1,2)),\nconstraint ck_subscriptions_status check (status in (0,1,2)),\nconstraint uq_subscriptions_tr_monitor_id unique (tr_monitor_id),\nconstraint pk_subscriptions primary key (id))\n;\n\ncreate table transactions (\nid                        bigserial not null,\ntr_monitor_id             varchar(255),\ncompany_id                bigint,\nhost_id                   varchar(255),\nhost_type                 integer,\ntr_id                     varchar(255),\ncreate_time               timestamp,\nupdate_time               timestamp,\nstart_date                timestamp,\nnext_billing_date         timestamp,\nlast_payment_date         timestamp,\nfinal_payment_date        timestamp,\npayer_info_first_name     varchar(255),\npayer_last_name           varchar(255),\npayer_id                  varchar(255),\npayer_email               varchar(255),\nmonitor_time_of_creation  timestamp,\ntransaction               varchar(255),\nconstraint ck_transactions_host_type check (host_type in (0,1,2)),\nconstraint pk_transactions primary key (id))\n;\n\ncreate table users (\nid                        bigserial not null,\nfirst_name                varchar(255) not null,\nlast_name                 varchar(255) not null,\nemail                     varchar(255) not null,\npassword                  varchar(255) not null,\ncompany_id                bigint,\nis_verified               boolean,\nemail_code                varchar(255),\ndate_of_creation          timestamp,\nrole_id                   integer,\ntimeZone                  varchar(255),\nreceive_notifications     boolean,\nweekly_report_notifications boolean,\nbilling_notifications     boolean,\ncrashes_notifications     boolean,\ntrends_notifications      boolean,\nconstraint ck_users_role_id check (role_id in (0,1,2)),\nconstraint uq_users_email unique (email),\nconstraint pk_users primary key (id))\n;\n\ncreate table trigger (\nid                        bigserial not null,\nhost_name                 varchar(255),\ntrigger_id                varchar(255),\nexpression                varchar(255),\ndescription               varchar(255),\ncomments                  varchar(255),\nhost_id                   bigint,\nconstraint uq_trigger_trigger_id unique (trigger_id),\nconstraint pk_trigger primary key (id))\n;\n\nalter table company_invite add constraint fk_company_invite_company_1 foreign key (company_id) references company (id);\ncreate index ix_company_invite_company_1 on company_invite (company_id);\nalter table company_trial add constraint fk_company_trial_company_2 foreign key (company_id) references company (id);\ncreate index ix_company_trial_company_2 on company_trial (company_id);\nalter table host add constraint fk_host_hostCompany_3 foreign key (host_company_id) references company (id);\ncreate index ix_host_hostCompany_3 on host (host_company_id);\nalter table issue add constraint fk_issue_company_4 foreign key (company_id) references company (id);\ncreate index ix_issue_company_4 on issue (company_id);\nalter table magento_host add constraint fk_magento_host_hostCompany_5 foreign key (host_company_id) references company (id);\ncreate index ix_magento_host_hostCompany_5 on magento_host (host_company_id);\nalter table promo_code add constraint fk_promo_code_company_6 foreign key (company_id) references company (id);\ncreate index ix_promo_code_company_6 on promo_code (company_id);\nalter table subscriptions add constraint fk_subscriptions_company_7 foreign key (company_id) references company (id);\ncreate index ix_subscriptions_company_7 on subscriptions (company_id);\nalter table users add constraint fk_users_company_8 foreign key (company_id) references company (id);\ncreate index ix_users_company_8 on users (company_id);\nalter table trigger add constraint fk_trigger_host_9 foreign key (host_id) references host (id);\ncreate index ix_trigger_host_9 on trigger (host_id);	drop table if exists company cascade;\n\ndrop table if exists company_invite cascade;\n\ndrop table if exists company_trial cascade;\n\ndrop table if exists host cascade;\n\ndrop table if exists issue cascade;\n\ndrop table if exists magento_host cascade;\n\ndrop table if exists promo_code cascade;\n\ndrop table if exists ratePlans cascade;\n\ndrop table if exists password_token cascade;\n\ndrop table if exists subscriptions cascade;\n\ndrop table if exists transactions cascade;\n\ndrop table if exists users cascade;\n\ndrop table if exists trigger cascade;	applied	
\.


--
-- Data for Name: promo_code; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.promo_code (id, company_id, promocode, is_used, time_of_creation, time_expiration, time_when_used) FROM stdin;
\.


--
-- Name: promo_code_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.promo_code_id_seq', 1, false);


--
-- Data for Name: rateplans; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.rateplans (id, nameofplan, hosttype, price, description) FROM stdin;
2	Linux	0	25	\N
1	Mongo	1	50	\N
\.


--
-- Name: rateplans_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.rateplans_id_seq', 1, false);


--
-- Data for Name: subscriptions; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.subscriptions (id, company_id, host_id, tr_monitor_id, host_type, quota_id, status, next_billing_date, last_payment_date, final_payment_date, monitor_time_of_creation, is_trial) FROM stdin;
1	1	1	b7ec7426da632d3114065f6fe41edc71841d3578bf35c27c8452e6f31cc3c370	\N	I-CJ3ST661HNJJ	0	2018-08-23 13:00:00	2018-08-23 15:58:28	1901-12-13 22:47:56	2018-08-22 18:11:42.733	f
5	2	2	8105281dfff6040c906629e47c306bca6101d847a45abfef5b53ae83034c2094	\N	\N	0	\N	2018-08-23 16:22:33.342	\N	2018-08-23 16:22:33.339	f
6	3	3	813e58d70390129c4eff73d91e2d0cb414615904ea673238cebc4c87ae255046	\N	\N	0	\N	2018-08-23 16:26:43.432	\N	2018-08-23 16:26:43.431	f
7	15	\N	7d83394911976dddcc685c747d4d9f3cc2b468998709e9b6f90ce05dcb7eba29	0	\N	\N	\N	\N	\N	2018-08-23 16:58:30.635	f
\.


--
-- Name: subscriptions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.subscriptions_id_seq', 7, true);


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.transactions (id, tr_monitor_id, company_id, host_id, host_type, tr_id, create_time, update_time, start_date, next_billing_date, last_payment_date, final_payment_date, payer_info_first_name, payer_last_name, payer_id, payer_email, monitor_time_of_creation, transaction) FROM stdin;
\.


--
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.transactions_id_seq', 1, false);


--
-- Data for Name: trigger; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.trigger (id, host_name, trigger_id, expression, description, comments, host_id) FROM stdin;
\.


--
-- Name: trigger_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.trigger_id_seq', 1, false);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: monitor
--

COPY public.users (id, first_name, last_name, email, password, company_id, is_verified, email_code, date_of_creation, role_id, timezone, receive_notifications, weekly_report_notifications, billing_notifications, crashes_notifications, trends_notifications) FROM stdin;
1	Ol	Iv	oleg.ivashko@yandex.ua	$2a$10$4SScCigHI3wCIIXO3ApXKuMQ2HXbzK/STw5cxCrD1XTl/SGymBLBe	15	t	\N	2018-08-22 18:11:42.586	1		t	t	t	t	t
2	uikyuky	uykyuky	yu6ty7u67i8@mailinator.com	$2a$10$u46siCrk1HXDqBV2U7F4XOmmt0.4FWD85LxQj3Cc4E9ictZV/Cgvm	2	f	75bfbfcb-bbe6-4d6a-b72f-ecf8fc5a098b	2018-08-23 16:22:33.325	1		t	t	t	t	t
3	rthrhrh	rthrthrth	uikukyukt6y7kiyu@mailinator.com	$2a$10$NbAOTQzAmAU8Gvkz3uh4h.i6h/5ODiHTU7uSU6AybX4V78M6yDLWm	15	t	\N	2018-08-23 16:26:43.408	1		t	t	t	t	t
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: monitor
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- Name: pk_company; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT pk_company PRIMARY KEY (id);


--
-- Name: pk_company_invite; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_invite
    ADD CONSTRAINT pk_company_invite PRIMARY KEY (id);


--
-- Name: pk_company_trial; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_trial
    ADD CONSTRAINT pk_company_trial PRIMARY KEY (id);


--
-- Name: pk_host; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.host
    ADD CONSTRAINT pk_host PRIMARY KEY (id);


--
-- Name: pk_issue; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.issue
    ADD CONSTRAINT pk_issue PRIMARY KEY (id);


--
-- Name: pk_magento_host; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.magento_host
    ADD CONSTRAINT pk_magento_host PRIMARY KEY (id);


--
-- Name: pk_password_token; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.password_token
    ADD CONSTRAINT pk_password_token PRIMARY KEY (id);


--
-- Name: pk_promo_code; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.promo_code
    ADD CONSTRAINT pk_promo_code PRIMARY KEY (id);


--
-- Name: pk_rateplans; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.rateplans
    ADD CONSTRAINT pk_rateplans PRIMARY KEY (id);


--
-- Name: pk_subscriptions; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.subscriptions
    ADD CONSTRAINT pk_subscriptions PRIMARY KEY (id);


--
-- Name: pk_transactions; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT pk_transactions PRIMARY KEY (id);


--
-- Name: pk_trigger; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.trigger
    ADD CONSTRAINT pk_trigger PRIMARY KEY (id);


--
-- Name: pk_users; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);


--
-- Name: play_evolutions_pkey; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.play_evolutions
    ADD CONSTRAINT play_evolutions_pkey PRIMARY KEY (id);


--
-- Name: uq_company_invite_company_id; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_invite
    ADD CONSTRAINT uq_company_invite_company_id UNIQUE (company_id);


--
-- Name: uq_company_name; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT uq_company_name UNIQUE (name);


--
-- Name: uq_company_trial_company_id; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_trial
    ADD CONSTRAINT uq_company_trial_company_id UNIQUE (company_id);


--
-- Name: uq_magento_host_server_name; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.magento_host
    ADD CONSTRAINT uq_magento_host_server_name UNIQUE (server_name);


--
-- Name: uq_subscriptions_tr_monitor_id; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.subscriptions
    ADD CONSTRAINT uq_subscriptions_tr_monitor_id UNIQUE (tr_monitor_id);


--
-- Name: uq_trigger_trigger_id; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.trigger
    ADD CONSTRAINT uq_trigger_trigger_id UNIQUE (trigger_id);


--
-- Name: uq_users_email; Type: CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uq_users_email UNIQUE (email);


--
-- Name: ix_company_invite_company_1; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_company_invite_company_1 ON public.company_invite USING btree (company_id);


--
-- Name: ix_company_trial_company_2; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_company_trial_company_2 ON public.company_trial USING btree (company_id);


--
-- Name: ix_host_hostcompany_3; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_host_hostcompany_3 ON public.host USING btree (host_company_id);


--
-- Name: ix_issue_company_4; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_issue_company_4 ON public.issue USING btree (company_id);


--
-- Name: ix_magento_host_hostcompany_5; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_magento_host_hostcompany_5 ON public.magento_host USING btree (host_company_id);


--
-- Name: ix_promo_code_company_6; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_promo_code_company_6 ON public.promo_code USING btree (company_id);


--
-- Name: ix_subscriptions_company_7; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_subscriptions_company_7 ON public.subscriptions USING btree (company_id);


--
-- Name: ix_trigger_host_9; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_trigger_host_9 ON public.trigger USING btree (host_id);


--
-- Name: ix_users_company_8; Type: INDEX; Schema: public; Owner: monitor
--

CREATE INDEX ix_users_company_8 ON public.users USING btree (company_id);


--
-- Name: fk_company_invite_company_1; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_invite
    ADD CONSTRAINT fk_company_invite_company_1 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: fk_company_trial_company_2; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.company_trial
    ADD CONSTRAINT fk_company_trial_company_2 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: fk_host_hostcompany_3; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.host
    ADD CONSTRAINT fk_host_hostcompany_3 FOREIGN KEY (host_company_id) REFERENCES public.company(id);


--
-- Name: fk_issue_company_4; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.issue
    ADD CONSTRAINT fk_issue_company_4 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: fk_magento_host_hostcompany_5; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.magento_host
    ADD CONSTRAINT fk_magento_host_hostcompany_5 FOREIGN KEY (host_company_id) REFERENCES public.company(id);


--
-- Name: fk_promo_code_company_6; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.promo_code
    ADD CONSTRAINT fk_promo_code_company_6 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: fk_subscriptions_company_7; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.subscriptions
    ADD CONSTRAINT fk_subscriptions_company_7 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: fk_trigger_host_9; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.trigger
    ADD CONSTRAINT fk_trigger_host_9 FOREIGN KEY (host_id) REFERENCES public.host(id);


--
-- Name: fk_users_company_8; Type: FK CONSTRAINT; Schema: public; Owner: monitor
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_users_company_8 FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

