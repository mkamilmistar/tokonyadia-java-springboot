/*
 Navicat PostgreSQL Data Transfer

 Source Server         : mandiri-academy
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : localhost:5432
 Source Catalog        : tokonyadia_final
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 25/04/2021 20:32:00
*/


-- ----------------------------
-- Table structure for m_customers
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_customers";
CREATE TABLE "public"."m_customers" (
  "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "email" varchar(30) COLLATE "pg_catalog"."default",
  "phone_number" varchar(15) COLLATE "pg_catalog"."default",
  "address" varchar(50) COLLATE "pg_catalog"."default",
  "status" int4
)
;
ALTER TABLE "public"."m_customers" OWNER TO "postgres";

-- ----------------------------
-- Records of m_customers
-- ----------------------------
BEGIN;
INSERT INTO "public"."m_customers" VALUES ('4028abff7902888301790288eb8e0001', 'Kamil', 'Kamil@gmail.com', '082283777098', 'Jl. Pepaya Sungai Salak', 1);
INSERT INTO "public"."m_customers" VALUES ('4028abff790400d30179040e6d930005', 'Maudy Haikal', 'maudy@gmail.com', '082283777098', 'Jl. Pepaya Sungai Salak', 1);
INSERT INTO "public"."m_customers" VALUES ('4028abff79026817017902682b360000', 'Melia Suspariana Edited', 'melia@gmail.com', '082283777098', 'Jl. Pepaya Sungai Salak', 1);
INSERT INTO "public"."m_customers" VALUES ('4028abff79090045017909147f740000', 'Melia Suspariana', 'melia@gmail.com', '082283777098', 'Jl. Pepaya Sungai Salak', 1);
COMMIT;

-- ----------------------------
-- Table structure for m_merchants
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_merchants";
CREATE TABLE "public"."m_merchants" (
  "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "siup" varchar(100) COLLATE "pg_catalog"."default",
  "address" varchar(50) COLLATE "pg_catalog"."default",
  "email" varchar(30) COLLATE "pg_catalog"."default",
  "phone_number" varchar(15) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."m_merchants" OWNER TO "postgres";

-- ----------------------------
-- Records of m_merchants
-- ----------------------------
BEGIN;
INSERT INTO "public"."m_merchants" VALUES ('4028abff7902903101790290954b0000', 'Serba Ada', 'xxx-xxx-xxx', 'Jl. Marpoyan Damai Pekanbaru', 'serbaada@gmail.com', '082283777098');
INSERT INTO "public"."m_merchants" VALUES ('4028abff79029031017902920cb70001', 'Toko Mulia', 'xxx-xxx-xxx', 'Jl. Pandau Pekanbaru', 'tokomulia@gmail.com', '082283777098');
INSERT INTO "public"."m_merchants" VALUES ('4028abff790400d30179040349970001', 'Sociolla Store', 'xxx-xxx-xxx', 'Jl. Pepaya Sungai Salak', 'socialla@gmail.com', '082283777098');
COMMIT;

-- ----------------------------
-- Table structure for m_products
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_products";
CREATE TABLE "public"."m_products" (
  "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "price" numeric,
  "status" int4,
  "stock" int4,
  "created_at" date,
  "updated_at" date,
  "merchant_id" varchar(32) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."m_products" OWNER TO "postgres";

-- ----------------------------
-- Records of m_products
-- ----------------------------
BEGIN;
INSERT INTO "public"."m_products" VALUES ('4028abff7902ae4f017902ae9f9f0000', 'Indomie', 3500, 1, 27, '2021-04-24', '2021-04-24', '4028abff7902903101790290954b0000');
INSERT INTO "public"."m_products" VALUES ('4028abff7902ae4f017902af0aac0001', 'Shampoo LifeBuoy Edited', 10000, 0, 0, '2021-04-24', '2021-04-24', '4028abff7902903101790290954b0000');
INSERT INTO "public"."m_products" VALUES ('4028abff790400d30179040c296f0004', 'The Ordinary', 140000, 1, 10, '2021-04-24', '2021-04-25', '4028abff790400d30179040349970001');
COMMIT;

-- ----------------------------
-- Table structure for t_purchases
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_purchases";
CREATE TABLE "public"."t_purchases" (
  "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "purchase_date" date,
  "customer_id" varchar(100) COLLATE "pg_catalog"."default",
  "product_id" varchar(100) COLLATE "pg_catalog"."default",
  "quantity" int2
)
;
ALTER TABLE "public"."t_purchases" OWNER TO "postgres";

-- ----------------------------
-- Records of t_purchases
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_purchases" VALUES ('7e008080790304a001790305fb690000', '2021-04-24', '4028abff79026817017902682b360000', '4028abff7902ae4f017902af0aac0001', 2);
INSERT INTO "public"."t_purchases" VALUES ('4028abff79034bb30179034ef5d40000', '2021-04-24', '4028abff79026817017902682b360000', '4028abff7902ae4f017902af0aac0001', 2);
INSERT INTO "public"."t_purchases" VALUES ('4028abff7903d360017903d3d6d40000', '2021-04-24', '4028abff7902888301790288eb8e0001', '4028abff7902ae4f017902af0aac0001', 5);
INSERT INTO "public"."t_purchases" VALUES ('4028abff7903d360017903d430200001', '2021-04-24', '4028abff7902888301790288eb8e0001', '4028abff7902ae4f017902ae9f9f0000', 3);
INSERT INTO "public"."t_purchases" VALUES ('4028abff7903ddad017903ddbf2c0000', '2021-04-24', '4028abff7902888301790288eb8e0001', '4028abff7902ae4f017902af0aac0001', 6);
INSERT INTO "public"."t_purchases" VALUES ('4028abff790400d301790401c3df0000', '2021-04-24', '4028abff7902888301790288eb8e0001', '4028abff7902ae4f017902af0aac0001', 1);
INSERT INTO "public"."t_purchases" VALUES ('4028abff790400d30179040ea1680006', '2021-04-24', '4028abff790400d30179040e6d930005', '4028abff790400d30179040c296f0004', 12);
INSERT INTO "public"."t_purchases" VALUES ('4028abff790400d30179040f2d830007', '2021-04-24', '4028abff790400d30179040e6d930005', '4028abff790400d30179040c296f0004', 1);
INSERT INTO "public"."t_purchases" VALUES ('4028abff7909244f01790925c27f0000', '2021-04-25', '4028abff790400d30179040e6d930005', '4028abff790400d30179040c296f0004', 1);
INSERT INTO "public"."t_purchases" VALUES ('4028abff7909244f0179092943aa0001', '2021-04-25', '4028abff790400d30179040e6d930005', '4028abff790400d30179040c296f0004', 1);
COMMIT;

-- ----------------------------
-- Primary Key structure for table m_customers
-- ----------------------------
ALTER TABLE "public"."m_customers" ADD CONSTRAINT "m_customer_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table m_merchants
-- ----------------------------
ALTER TABLE "public"."m_merchants" ADD CONSTRAINT "m_merchant_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table m_products
-- ----------------------------
ALTER TABLE "public"."m_products" ADD CONSTRAINT "m_products_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_purchases
-- ----------------------------
ALTER TABLE "public"."t_purchases" ADD CONSTRAINT "m_purchases_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table m_products
-- ----------------------------
ALTER TABLE "public"."m_products" ADD CONSTRAINT "fk_product_merchant" FOREIGN KEY ("merchant_id") REFERENCES "public"."m_merchants" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table t_purchases
-- ----------------------------
ALTER TABLE "public"."t_purchases" ADD CONSTRAINT "fk_purcase_customer" FOREIGN KEY ("customer_id") REFERENCES "public"."m_customers" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."t_purchases" ADD CONSTRAINT "fk_purchase_product" FOREIGN KEY ("product_id") REFERENCES "public"."m_products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
