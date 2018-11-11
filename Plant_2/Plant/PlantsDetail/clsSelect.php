<?php
		if(function_exists($_GET['f'])) 
		{
			$_GET['f']();
		}
	//------------------------------------------------------------------------------------------------------------- tblLoginSelectForLogin
		 
		//------------------------------------------------------------------------------------------------------------- tblDepartmentSelectAll
	function tblVegitablesSelectAll()
		{
			include("dbConn.php");	
			$check=0;
			$sql="SELECT * FROM `tblvegitables` ORDER BY Name ASC ";
			$result=$mysqli->query($sql);
			while($e=mysqli_fetch_assoc($result))
			{
				$output['member'][]=$e;
				$check=1;
			}
			if($check ==0)
			{
				$output['member'][]="";
			}
			$mysqli->close();	
			print(json_encode($output)); 
		}
		
		//------------------------------------------------------------------------------------------------------------- tblSubDepartmentSelectByDeptId
	function tblVegitablesSelectById()
		{
			include("dbConn.php");	
			$intId=$_GET['intId'];
			$check=0;
			$sql="SELECT * FROM `tblvegitables` WHERE `intId` = '$intId'  ";
			$result=$mysqli->query($sql);
			while($e=mysqli_fetch_assoc($result))
			{
				$output['member'][]=$e;
				$check=1;
			}
			if($check ==0)
			{
				$output['member'][]="";
			}
			$mysqli->close();	
			print(json_encode($output)); 
		}
		
//------------------------------------------------------------------------------------------------------------- tblDepartmentSelectAll
	function tblPlantsSelectAll()
		{
			include("dbConn.php");	
			$check=0;
			$sql="SELECT * FROM `tblplants` ORDER BY Name ASC ";
			$result=$mysqli->query($sql);
			while($e=mysqli_fetch_assoc($result))
			{
				$output['member'][]=$e;
				$check=1;
			}
			if($check ==0)
			{
				$output['member'][]="";
			}
			$mysqli->close();	
			print(json_encode($output)); 
		}
		
		//------------------------------------------------------------------------------------------------------------- tblSubDepartmentSelectByDeptId
	function tblPlantsSelectById()
		{
			include("dbConn.php");	
			$intId=$_GET['intId'];
			$check=0;
			$sql="SELECT * FROM `tblplants` WHERE `intId` = '$intId'  ";
			$result=$mysqli->query($sql);
			while($e=mysqli_fetch_assoc($result))
			{
				$output['member'][]=$e;
				$check=1;
			}
			if($check ==0)
			{
				$output['member'][]="";
			}
			$mysqli->close();	
			print(json_encode($output)); 
		}
		
		
		?>
		
	