CREATE TABLE "team" (
  "team_id" integer PRIMARY KEY,
  "title" varchar(40) NOT NULL,
  "description" varchar(2000),
  "active" boolean,
  "created_at" timestamp,
  "created_by_user_id" integer,
  "last_modified_at" timestamp NOT NULL DEFAULT (now()),
  "last_modified_by_user_id" integer
);

CREATE TABLE "users" (
  "user_id" integer PRIMARY KEY,
  "name" varchar(200) NOT NULL,
  "username" varchar(100) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "email" varchar(255) UNIQUE,
  "role" varchar(50),
  "team_id" integer,
  "active" boolean NOT NULL,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "created_by_user_id" integer,
  "last_modified_at" timestamp NOT NULL DEFAULT (now()),
  "last_modified_by_user_id" integer
);

CREATE TABLE "task_status" (
  "task_status_id" integer PRIMARY KEY,
  "title" varchar(40) NOT NULL
);

CREATE TABLE "task" (
  "task_id" integer PRIMARY KEY,
  "title" varchar(40) NOT NULL,
  "description" text,
  "status_id" integer NOT NULL,
  "created_by_user_id" integer NOT NULL,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "last_modified_at" timestamp NOT NULL DEFAULT (now()),
  "last_modified_by_user_id" integer,
  "start_date" timestamp,
  "started_by_user_id" integer,
  "end_date" timestamp,
  "ended_by_user_id" integer
);

CREATE TABLE "task_pause" (
  "task_pause_id" integer PRIMARY KEY,
  "task_id" integer NOT NULL,
  "paused_at" timestamp NOT NULL DEFAULT (now()),
  "paused_by_user_id" integer NOT NULL,
  "resumed_at" timestamp,
  "resumed_by_user_id" integer,
  "reason" text
);

CREATE TABLE "tags" (
  "tag_id" integer PRIMARY KEY,
  "title" varchar(40) NOT NULL,
  "description" varchar(200),
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "created_by_user_id" integer NOT NULL,
  "last_modified_at" timestamp NOT NULL DEFAULT (now()),
  "last_modified_by_user_id" integer,
  "team_id" integer NOT NULL
);

CREATE TABLE "task_tags" (
  "task_tag_id" integer PRIMARY KEY,
  "task_id" integer NOT NULL,
  "tag_id" integer NOT NULL
);

ALTER TABLE "team" ADD FOREIGN KEY ("created_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "team" ADD FOREIGN KEY ("last_modified_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "users" ADD FOREIGN KEY ("team_id") REFERENCES "team" ("team_id");

ALTER TABLE "users" ADD FOREIGN KEY ("created_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "users" ADD FOREIGN KEY ("last_modified_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task" ADD FOREIGN KEY ("status_id") REFERENCES "task_status" ("task_status_id");

ALTER TABLE "task" ADD FOREIGN KEY ("created_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task" ADD FOREIGN KEY ("last_modified_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task" ADD FOREIGN KEY ("started_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task" ADD FOREIGN KEY ("ended_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task_pause" ADD FOREIGN KEY ("task_id") REFERENCES "task" ("task_id");

ALTER TABLE "task_pause" ADD FOREIGN KEY ("paused_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "task_pause" ADD FOREIGN KEY ("resumed_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "tags" ADD FOREIGN KEY ("created_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "tags" ADD FOREIGN KEY ("last_modified_by_user_id") REFERENCES "users" ("user_id");

ALTER TABLE "tags" ADD FOREIGN KEY ("team_id") REFERENCES "team" ("team_id");

ALTER TABLE "task_tags" ADD FOREIGN KEY ("task_id") REFERENCES "task" ("task_id");

ALTER TABLE "task_tags" ADD FOREIGN KEY ("tag_id") REFERENCES "tags" ("tag_id");
