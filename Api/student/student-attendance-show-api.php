<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($history_id,$course_code)
		{
			 $response = array();
		  $i= 1;
		 
	 

		 $show_attandance = mysqli_query($this->connection,"SELECT * FROM attandance WHERE course_id = '$course_code' and student_history_id = '$history_id'");
        $attendance['attendance'] = array();
 while ($result = mysqli_fetch_array($show_attandance)) { 
                        $date = $result['date']."-".$result['month']."-".$result['year'];
                        $topic = $result['topic'];
                        $status = $result['status'];
                     $response=array(
						'Date'=>$date,
						'State'=>$status,
						'Topic' =>$topic				 
			 );
			 array_push( $attendance['attendance'],$response);
                             
           }
				 
				echo json_encode($attendance);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['course_code'],$_POST['history_id'])) {
		 
		 
		$course_code = $_POST['course_code'];
		$history_id = $_POST['history_id'];
		 
	 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($course_code) && !empty($history_id)){
			
		 
			$user-> AssignmentDetails($history_id,$course_code);
			
		}else{
			echo json_encode("No Attendence  Found");
		}
		
	}
