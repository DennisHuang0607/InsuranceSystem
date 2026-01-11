-- 1.檢查並創建資料庫

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'insurancesystem')
BEGIN
    CREATE DATABASE [insurancesystem];
END
GO

--2. 設定資料庫內容

USE [insurancesystem];
GO

-- 2.1 創建 Sequence (解決高併發流水號問題) [新增內容]

-- 用於保單編號 (PolicyNumber)，起始值為 2(因為初始資料已佔用 1)，每次遞增 1
IF EXISTS (SELECT * FROM sys.sequences WHERE name = N'seq_PolicyNumber')
    DROP SEQUENCE seq_PolicyNumber;
GO
CREATE SEQUENCE seq_PolicyNumber
    START WITH 2
    INCREMENT BY 1;
GO

-- 用於保險員編號 (InsurerId)，起始值為 2 (因為初始資料已佔用 INS000001)
IF EXISTS (SELECT * FROM sys.sequences WHERE name = N'seq_InsurerId')
    DROP SEQUENCE seq_InsurerId;
GO
CREATE SEQUENCE seq_InsurerId
    START WITH 2
    INCREMENT BY 1;
GO

--3. 創建表格結構

--Table [dbo].[Admin]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[Admin](
    [name] [nvarchar](100) NOT NULL,
    [account_id] [nvarchar](100) NOT NULL,
    [password] [nvarchar](100) NOT NULL,
    [email] [nvarchar](100) NOT NULL,
    CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
    (
        [account_id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

--Table [dbo].[InsuranceCompany]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[InsuranceCompany](
    [company_id] [int] IDENTITY(1,1) NOT NULL,
    [company_name] [nvarchar](100) NOT NULL,
    [address] [nvarchar](200) NULL,
    [telephone] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
    [company_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
CONSTRAINT [UQ_CompanyName] UNIQUE NONCLUSTERED 
(
    [company_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
);
GO


--Table [dbo].[InsuranceType]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[InsuranceType](
    [insurance_type_id] [int] IDENTITY(1,1) NOT NULL,
    [type_name] [nvarchar](50) NOT NULL,
    [description] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
    [insurance_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
    [type_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO


--Table [dbo].[Insurer]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[Insurer](
    [insurer_id] [nvarchar](50) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    [account_id] [nvarchar](50) NOT NULL,
    [password] [nvarchar](255) NOT NULL,
    [phone] [nvarchar](20) NULL,
    [email] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
    [insurer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
    [account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

--Table [dbo].[Policy]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[Policy](
    [policy_id] [int] IDENTITY(1,1) NOT NULL,
    [policy_number] [nvarchar](50) NOT NULL,
    [insurance_type_id] [int] NOT NULL,
    [company_id] [int] NOT NULL,
    [insurer_id] [nvarchar](50) NOT NULL,
    [insured_amount] [decimal](15, 2) NOT NULL,
    [accept_date] [date] NOT NULL,
    [begin_date] [date] NOT NULL,
    [end_date] [date] NOT NULL,
    [payment_type] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
    [policy_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

--Table [dbo].[PolicyPerson]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[PolicyPerson](
    [person_id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    [id_number] [nvarchar](10) NOT NULL,
    [gender] [char](1) NULL,
    [birth_date] [date] NOT NULL,
    [phone] [nvarchar](20) NOT NULL,
    [email] [nvarchar](100) NULL,
    [address] [nvarchar](200) NULL,
    [occupation] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
    [person_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
CONSTRAINT [UQ_PolicyPerson_IdNumber] UNIQUE NONCLUSTERED 
(
    [id_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

--Table [dbo].[PolicyPersonRole]
--
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[PolicyPersonRole](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [policy_id] [int] NOT NULL,
    [person_id] [int] NOT NULL,
    [role] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
CONSTRAINT [UQ_PolicyPersonRole] UNIQUE NONCLUSTERED 
(
    [policy_id] ASC,
    [person_id] ASC,
    [role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- 4. 創建外部鍵(Foreign Keys)和檢查約束(Check Constraints)

--Policy Table FK
ALTER TABLE [dbo].[Policy] WITH CHECK ADD FOREIGN KEY([company_id])
REFERENCES [dbo].[InsuranceCompany] ([company_id]);
ALTER TABLE [dbo].[Policy] WITH CHECK ADD FOREIGN KEY([insurance_type_id])
REFERENCES [dbo].[InsuranceType] ([insurance_type_id]);
ALTER TABLE [dbo].[Policy] WITH CHECK ADD FOREIGN KEY([insurer_id])
REFERENCES [dbo].[Insurer] ([insurer_id]);
GO

--PolicyPersonRole Table FK and Check
ALTER TABLE [dbo].[PolicyPersonRole] WITH CHECK ADD CONSTRAINT [FK_PolicyPersonRole_Policy] FOREIGN KEY([policy_id])
REFERENCES [dbo].[Policy] ([policy_id]);
ALTER TABLE [dbo].[PolicyPersonRole] CHECK CONSTRAINT [FK_PolicyPersonRole_Policy];

ALTER TABLE [dbo].[PolicyPersonRole] WITH CHECK ADD CONSTRAINT [FK_PolicyPersonRole_PolicyPerson] FOREIGN KEY([person_id])
REFERENCES [dbo].[PolicyPerson] ([person_id]);
ALTER TABLE [dbo].[PolicyPersonRole] CHECK CONSTRAINT [FK_PolicyPersonRole_PolicyPerson];

ALTER TABLE [dbo].[PolicyPersonRole] WITH CHECK ADD CONSTRAINT [CK_PolicyPersonRole_Role] CHECK (([role]=N'受益人' OR [role]=N'被保人' OR [role]=N'投保人'));
ALTER TABLE [dbo].[PolicyPersonRole] CHECK CONSTRAINT [CK_PolicyPersonRole_Role];
GO

-- 5.插入初始資料 

--Table: Admin，帳號/密碼為admin/123
INSERT INTO [dbo].[Admin] ([name], [account_id], [password], [email])
VALUES (N'系統管理員', N'admin', N'$2a$12$wANny62wqrCJxvlEiIYckuC1viIJsd4EjWX2apSrOz15LT..meCWa', N'admin@insurancesystem.com');
GO
--Table: InsuranceCompany
INSERT INTO [dbo].[InsuranceCompany] ([company_name], [address], [telephone])
VALUES (N'台中保險', N'台中市北屯區一條路11號', N'04-2622-1234'),
               (N'台北保險', N'台北市信義區二條路22號', N'02-2785-1234');
GO
--Table: InsuranceType
INSERT INTO [dbo].[InsuranceType] ([type_name], [description])
VALUES (N'強制險', N'法律強制投保，保障車禍對方體傷。'),
               (N'醫療險', N'實支實付或日額給付住院費用。'),
               (N'死亡險', N'被保險人身故給付。');
GO
--Table: Insurer，帳號/密碼為test/123
INSERT INTO [dbo].[Insurer] ([insurer_id], [name], [account_id], [password], [phone], [email])
VALUES (N'INS000001', N'測試用', N'test', N'$2a$12$yGSnZsbXShDkC2HcjY.LDeYFEKP.QWv4hPtINbVPnYLfKYMaKbUi6', N'0912-345-678', N'test@gmail.com');
GO
--Table: PolicyPerson
INSERT INTO [dbo].[PolicyPerson] ([name], [id_number], [gender], [birth_date], [phone], [email], [address], [occupation])
VALUES (N'陳小美', N'A123456789', N'F', '1990-05-20', N'0987-654-321', N'cm@gmail.com', N'新北市板橋區文化路一段1號', N'護理師'),
               (N'黃小東', N'L123987789', N'M', '1988-12-05', N'0955-123-241', N'hd@gmail.com', N'新北市板橋區文化路一段1號', N'醫生');
GO

--6.檢查並創建新的登入
USE [master];
GO
--刪除已存在的登入，以防重複執行腳本時報錯
IF EXISTS (SELECT * FROM sys.server_principals WHERE name = N'app_user')
BEGIN
    DROP LOGIN app_user;
END
GO
CREATE LOGIN app_user WITH PASSWORD = N'Test!123', CHECK_POLICY = ON;
GO

--7.在insurancesystem資料庫中檢查並創建使用者(User)
USE [insurancesystem];
GO
--刪除已存在的User
IF EXISTS (SELECT * FROM sys.database_principals WHERE name = N'app_user')
BEGIN
    DROP USER app_user;
END
GO
CREATE USER app_user FOR LOGIN app_user;
GO

-- 8. 賦予該使用者權限
ALTER ROLE db_datareader ADD MEMBER app_user;
ALTER ROLE db_datawriter ADD MEMBER app_user;
GO

-- 9. 插入首筆保單資料 (示範關聯)
-- 注意：這裡假設前面的 IDENTITY 是從 1 開始自增
INSERT INTO [dbo].[Policy] (
    [policy_number], 
    [insurance_type_id], 
    [company_id], 
    [insurer_id], 
    [insured_amount], 
    [accept_date], 
    [begin_date], 
    [end_date], 
    [payment_type]
)
VALUES (
    N'PN' + CONVERT(NVARCHAR(8), GETDATE(), 112) + RIGHT('000000' + CAST(1 AS NVARCHAR(10)), 6),
    1,             -- 對應 '強制險' (InsuranceType ID)
    1,             -- 對應 '台中保險' (InsuranceCompany ID)
    N'INS000001',  -- 對應 '測試用' 員編
    1000.00,     -- 保費
    '2024-01-01',  -- 受理日期
    '2024-01-02',  -- 生效日期
    '2025-01-01',  -- 滿期日期
    N'annually'      -- 繳費方式
);

-- 取得剛剛生成的 PolicyId (SQL Server 內建變數)
DECLARE @current_policy_id INT = SCOPE_IDENTITY();

-- 10. 插入保單關係人角色 (將保單與人員 陳小美/黃小東 連結)
INSERT INTO [dbo].[PolicyPersonRole] ([policy_id], [person_id], [role])
VALUES 
    (@current_policy_id, 1, N'投保人'), -- 陳小美 作為投保人
    (@current_policy_id, 1, N'被保人'), -- 陳小美 同時作為被保人
    (@current_policy_id, 2, N'受益人'); -- 黃小東 作為受益人
GO

