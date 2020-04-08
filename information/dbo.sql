/*
 Navicat Premium Data Transfer

 Source Server         : sql server
 Source Server Type    : SQL Server
 Source Server Version : 15002000
 Source Host           : localhost:1433
 Source Catalog        : Commodity_Management_System
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002000
 File Encoding         : 65001

 Date: 08/04/2020 22:07:54
*/


-- ----------------------------
-- Table structure for Good
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Good]') AND type IN ('U'))
    DROP TABLE [dbo].[Good]
GO

CREATE TABLE [dbo].[Good] (
                              [GoodId] int  IDENTITY(1,1) NOT NULL,
                              [GoodName] nvarchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                              [GoodPrice] numeric(10,2)  NOT NULL,
                              [GoodNumber] int  NOT NULL,
                              [GoodNote] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[Good] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for Salesman
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Salesman]') AND type IN ('U'))
    DROP TABLE [dbo].[Salesman]
GO

CREATE TABLE [dbo].[Salesman] (
                                  [SaleId] int  IDENTITY(1,1) NOT NULL,
                                  [SaleName] nvarchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                  [SalePassword] nvarchar(32) COLLATE Chinese_PRC_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[Salesman] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for SoldNote
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SoldNote]') AND type IN ('U'))
    DROP TABLE [dbo].[SoldNote]
GO

CREATE TABLE [dbo].[SoldNote] (
                                  [SoldId] int  IDENTITY(1,1) NOT NULL,
                                  [GoodId] int  NOT NULL,
                                  [SaleId] int  NOT NULL,
                                  [SoldNumber] int  NOT NULL,
                                  [SoldDate] datetime DEFAULT (getdate()) NULL
)
GO

ALTER TABLE [dbo].[SoldNote] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Auto increment value for Good
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Good]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table Good
-- ----------------------------
ALTER TABLE [dbo].[Good] ADD CONSTRAINT [pk_good_goodid] PRIMARY KEY CLUSTERED ([GoodId])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Salesman
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Salesman]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table Salesman
-- ----------------------------
ALTER TABLE [dbo].[Salesman] ADD CONSTRAINT [pk_salesman_saleid] PRIMARY KEY CLUSTERED ([SaleId])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for SoldNote
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[SoldNote]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table SoldNote
-- ----------------------------
ALTER TABLE [dbo].[SoldNote] ADD CONSTRAINT [pk_soldnote_soldid] PRIMARY KEY CLUSTERED ([SoldId])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table SoldNote
-- ----------------------------
ALTER TABLE [dbo].[SoldNote] ADD CONSTRAINT [fk_good_goodid] FOREIGN KEY ([GoodId]) REFERENCES [dbo].[Good] ([GoodId]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[SoldNote] ADD CONSTRAINT [fk_salesman_saleid] FOREIGN KEY ([SaleId]) REFERENCES [dbo].[Salesman] ([SaleId]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

