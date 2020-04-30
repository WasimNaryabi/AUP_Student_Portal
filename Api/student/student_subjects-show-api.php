<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($discipline_id,$semester_id)
		{
			 
		 $select_courses = mysqli_query($this->connection,"SELECT * FROM courses WHERE discipline_id = '$discipline_id' and semester_id = '$semester_id'");



 $subject['subjects'] = array();                              
  while ($row = mysqli_fetch_array($select_courses)) {
                                  $course_name = $row['course_name'];
                                  $course_code = $row['course_id'];
                                   
                                   //here in this query the courses_outlines are stored
                              

				$response=array(
						'course_name'=>$course_name,
						'course_code'=>$course_code
						
			 );
			 array_push($subject['subjects'], $response);
			 }
				 
				echo json_encode($subject);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['semester_id'],$_POST['discipline_id'])) {
		 
		 
		$semester_id = $_POST['semester_id'];
		 
		$discipline_id = $_POST['discipline_id'];

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($discipline_id) && !empty($semester_id)){
			
		 
			$user-> AssignmentDetails($discipline_id,$semester_id);
			
		}else{
			echo json_encode("No Subject  Found");
		}
		
	}
