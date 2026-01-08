專案介紹

1\.嚴謹的權限控管：

區分管理員與保險員雙重角色

基於Spring Security實作路徑級別的存取控制，並採用BCrypt加密保障帳號安全

2\.自動化業務邏輯：

自動生成唯一保單編號及保險員編號，透過資料庫Sequence解決高併發下的重複問題

系統啟動時自動掃描並加密舊有的明文密碼

3\.高效的前後端互動：

使用AJAX非同步技術，實現無刷新頁面的資料更新與表單提交

前端整合Vue與jQuery，提供流暢的動態操作體驗

4\.資料完整性保障：

使用Spring Data JPA處理複雜關聯

實作@Transactional交易管理，確保多表寫入時的原子性

統一回傳格式，確保前端能精確捕捉成功、重複、或系統異常等狀態

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

核心邏輯：Vue3、JQuery、AJAX

互動元件：SweetAlert2

UI框架：Bootstrap5

----------------

維運與部署

版本控制：Git

容器化：Docker、Docker Compose

-------------

啟動方式--透過Docker

前置需求：

A.安裝Docker Desktop並啟動，下載路徑：https://www.docker.com/products/docker-desktop/

B.於GitHub下載專案並解壓縮，下載路徑：https://github.com/DennisHuang0607/InsuranceSystem.git

啟動步驟：

C.設定環境變數：在專案根目錄建立.env檔案，並設定資料庫密碼(大寫+小寫+數字+特殊符號+至少八個字元)->SA\_PASSWORD=*你的密碼*

D.打包專案：./mvnw clean package -DskipTests (請透過Windows PowerShell執行)

E.準備JAR檔：進入target資料夾，將生成的jar檔重新命名為insurancesystem.jar

F.啟動容器：docker-compose up -d (請透過Windows PowerShell執行)

G.訪問系統：首頁->http://localhost:8080/insurancesystem/home，資料庫連接埠->3306

視情況使用：

H.停止並刪除容器，同時刪除資料卷：docker-compose down -v

-------------

啟動方式--透過AWS

瀏覽器輸入URL：http://57.180.7.116:8080/insurancesystem/home

