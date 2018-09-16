<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script type="text/javascript" src="/Content/lib/jquery.min.js"></script>
  <script type="text/javascript">
    function connectTcp(type){
        $.post("/relay/connectServer", {type: type}, function(res){
            alert(res.message);
            $("#lockStatus").html(res.message);
        });
    }

   function openLock(lockId){
       $.post("/relay/openLock", {lockId: lockId}, function(res){
           alert(res.message);
       });
   }

    function closeLock(lockId){
        $.post("/relay/closeLock", {lockId: lockId}, function(res){
            alert(res.message);
        });
    }

    function openAllLock(){
        $.post("/relay/openAllLock", function(res){
            alert(res.message);
        });
    }

    function closeAllLock(){
        $.post("/relay/closeAllLock", function(res){
            alert(res.message);
        });
    }

    function openDrive(driveId){
        $.post("/relay/openDrive", {driveId: driveId}, function(res){
            alert(res.message);
        });
    }

    function closeDrive(driveId){
        $.post("/relay/closeDrive", {driveId: driveId}, function(res){
            alert(res.message);
        });
    }

  </script>
</head>
<body>
  <h3>当前柜子状态：<span id="lockStatus"></span></h3>
  <input type="button" value="连接柜子服务器" onclick="connectTcp(1)" /><br/>
  <input type="text" id="lockId" placeholder="柜子号" /><br/>
  <input type="button" value="打开柜子" onclick="openLock($('#lockId').val())" />
  <input type="button" value="关闭柜子" onclick="closeLock($('#lockId').val())" />

  <input type="button" value="打开所有柜子" onclick="openAllLock()" />
  <input type="button" value="关闭所有柜子" onclick="closeAllLock()" />
  <hr/>

  <h3>当前大门状态：<span id="menStatus"></span></h3>
  <input type="button" value="连接门设备服务器" onclick="connectTcp(2)" /><br/>
  <input type="text" id="driveId" placeholder="门设备命令号" /><br/>
  <input type="button" value="连接门设备命令号" onclick="openDrive($('#driveId').val())" />
  <input type="button" value="关闭门设备命令号" onclick="closeDrive($('#driveId').val())" />
</body>
</html>
