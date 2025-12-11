專案介紹
1\.嚴謹的權限控管：

區分管理員與保險員雙重角色

基於Spring Security實作路徑級別的存取控制，並採用BCrypt加密保障帳號安全

2\.自動化業務邏輯：

自動生成唯一保單編號，格式：PN+日期+流水號

系統啟動時自動掃描並加密舊有的明文密碼

3\.高效的前後端互動：

使用AJAX非同步技術，實現無刷新頁面的資料更新與表單提交

前端整合Vue與jQuery，提供流暢的動態操作體驗

4\.資料完整性保障：

使用Spring Data JPA處理複雜關聯

實作@Transactional交易管理，確保多表寫入時的原子性

-------------

後端技術

核心框架：Spring Boot

語言：Java17

安全性：Spring Security、BCrypt

資料庫：MSSQL

ORM框架：Spring Data JPA

SQL工具：JDBC Template

API設計：RESTful

依賴管理：Maven

-------------

前端技術

核心邏輯：Vue、JQuery、AJAX

互動元件：SweetAlert

UI框架：Bootstrap\

----------------

維運與部署

版本控制：Git

容器化：Docker、Docker Compose

-------------

啟動方式

前置需求：

A.安裝Docker Desktop並啟動，https://www.docker.com/products/docker-desktop/

B.到https://github.com/DennisHuang0607/InsuranceSystem.git，下載專案並解壓縮

啟動步驟：

C.設定環境變數：在專案根目錄建立.env檔案，並設定資料庫密碼(大寫+小寫+數字+特殊符號+至少八個字元)->SA\_PASSWORD=*你的密碼*

D.打包專案：./mvnw clean package -DskipTests

E.準備JAR檔：進入target資料夾，將生成的jar檔重新命名為insurancesystem.jar

F.啟動容器：docker-compose up -d

G.訪問系統：首頁->http://localhost:8080/insurancesystem/home，資料庫連接埠->3306

視情況使用：

H.停止並刪除容器，同時刪除資料卷：docker-compose down -v

